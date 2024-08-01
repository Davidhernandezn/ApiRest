package com.gs.training.petardocore.controller;

import com.gs.ftt.log.LogMonitor;
import com.gs.training.petardocore.dto.PersonaDto;
import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.exception.EMensajeException;
import com.gs.training.petardocore.mapper.PersonaMapper;
import com.gs.training.petardocore.model.CommonResponse;
import com.gs.training.petardocore.model.Persona;
import com.gs.training.petardocore.service.PersonaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

	@GetMapping("/persona/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> showById(@RequestParam(required = false) Long id) {
	    CommonResponse<Persona> response = personaService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	/**
	   public ResponseEntity<?> showById(@RequestParam(required = false) Long id) {
        Persona response = personaService.findById(id);
        if ("Operación exitosa".equals(response.getMensaje())) {
            return new ResponseEntity<>(personaMapper.toDTO(response.getResultado()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new EMensajeException(response.getCodigo(), response.getMensaje()), HttpStatus.valueOf(Integer.parseInt(response.getCodigo())));
        }
	}**/

	@PostMapping("persona")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody PersonaDto personaDto) {
		 LOGGER.info("POST banco-azteca/afore/personas/v1/personas request= {}", personaDto);
	        try {
	            CommonResponse<Persona> response = personaService.savePersona(personaDto);
	            return new ResponseEntity<>(response, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return null;
	        }
	    }
	

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
