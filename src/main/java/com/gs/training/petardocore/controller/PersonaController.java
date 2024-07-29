package com.gs.training.petardocore.controller;

import com.gs.ftt.log.LogMonitor;
import com.gs.training.petardocore.dto.PersonaDto;
import com.gs.training.petardocore.mapper.PersonaMapper;
import com.gs.training.petardocore.model.CommonResponse;
import com.gs.training.petardocore.model.GenericResponse;
import com.gs.training.petardocore.model.Persona;
import com.gs.training.petardocore.service.PersonaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${basePath}")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private PersonaMapper personaMapper;
	
	private static final LogMonitor LOGGER = new LogMonitor(PetardoCoreController.class);


	@GetMapping("/personas")
	@ResponseStatus(HttpStatus.OK)
	public List<PersonaDto> getAllPersonas() {
		return personaService.getAllPersonas().stream().map(personaMapper::toDTO).collect(Collectors.toList());
	}

	@GetMapping("/persona")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> showById(@RequestParam(required = false) Long id) {
		if (id == null) {
			return new ResponseEntity<>(createErrorResponse("Error: el parámetro ID es requerido."),
					HttpStatus.BAD_REQUEST);
		}
		Persona persona = personaService.findById(id);
		if (persona == null) {
			return new ResponseEntity<>(createErrorResponse("Error: la persona con ID: " + id + " no es válida."),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(personaMapper.toDTO(persona), HttpStatus.OK);
	}

	/**
	@PostMapping("/persona")
	 * @return 
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody PersonaDto personaDTO) {
		try {
			Persona persona = personaMapper.toEntity(personaDTO);
			Persona savedPersona = personaService.savePersona(persona);
			return new ResponseEntity<>(personaMapper.toDTO(savedPersona), HttpStatus.CREATED);
		} catch (DataAccessException exDt) {
			return new ResponseEntity<>(
					createErrorResponse("Error al realizar la inserción en la base de datos.", exDt),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}**/
	
	@PostMapping("/persona")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody PersonaDto personaDto) {
		LOGGER.info("POST banco-azteca/afore/personas/v1/personas request= {}", personaDto);
		CommonResponse<Persona> response = personaService.savePersona(personaDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}

	/**
	@PutMapping("/persona")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updatePerson(@RequestBody PersonaDto personaDTO, @RequestParam(required = false) Long id) {

		if (id == null) {
			return new ResponseEntity<>(createErrorResponse("Error: el parámetro ID es requerido."),
					HttpStatus.BAD_REQUEST);
		}
		
		Persona personaFindById = personaService.findById(id);
		if (personaFindById == null) {
			return new ResponseEntity<>(
					createErrorResponse(
							"Error: no se pudo editar, la persona ID: " + personaDTO.getId() + " no existe."),
					HttpStatus.NOT_FOUND);
		}

		try {
			personaDTO.setId(id);
			BeanUtils.copyProperties(personaFindById, personaDTO);
			Persona updatedPersona = personaService.savePersona(personaFindById);
			return new ResponseEntity<>(personaMapper.toDTO(updatedPersona), HttpStatus.CREATED);
		} catch (DataAccessException exDt) {
			return new ResponseEntity<>(
					createErrorResponse("Error al actualizar la persona en la base de datos.", exDt),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IllegalAccessException | InvocationTargetException e) {
			return new ResponseEntity<>(createErrorResponse("Error al copiar las propiedades de la persona.", e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}**/
	
	 @DeleteMapping("/persona/{id}")
	    public ResponseEntity<Void> deletePersona(@RequestParam(required = false) Long id) {
			if (id == null) {
			    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: el parámetro ID es requerido.");
			}
	        try {
	            personaService.deletePersona(id);
	            return ResponseEntity.noContent().build();
	        } catch (EntityNotFoundException ex) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona not found", ex);
	        } catch (Exception ex) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", ex);
	        }
	    }

	 
	private Map<String, Object> createErrorResponse(String message) {
		Map<String, Object> response = new HashMap<>();
		response.put("mensaje", message);
		return response;
	}

	private Map<String, Object> createErrorResponse(String message, Exception ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("mensaje", message);
		response.put("error",
				ex.getMessage().concat(": ").concat(ex.getCause() != null ? ex.getCause().getMessage() : ""));
		return response;
	}

	private Map<String, Object> createSuccessResponse(String message) {
		Map<String, Object> response = new HashMap<>();
		response.put("mensaje", message);
		return response;
	}
}
