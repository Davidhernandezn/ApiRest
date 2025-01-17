package com.gs.training.petardocore.controller;

import com.gs.ftt.log.LogMonitor;
import com.gs.training.petardocore.constant.PetardoCoreConstants;
import com.gs.training.petardocore.dto.PersonaDto;
import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.mapper.PersonaMapper;
import com.gs.training.petardocore.model.GenericResponse;
import com.gs.training.petardocore.model.Persona;
import com.gs.training.petardocore.service.PersonaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("${basePath}")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private PersonaMapper personaMapper;

	private static final LogMonitor LOGGER = new LogMonitor(PetardoCoreController.class);

	public PersonaController(PersonaService personaService) {
		this.personaService = personaService;
	}

	@GetMapping("/personas")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponse<List<PersonaDto>>> getAllPersonas() {
	    GenericResponse<List<PersonaDto>> response = personaService.getAllPersonas();
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@GetMapping("/persona/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponse> findById(@RequestParam Long idPersona) {
		try {
			GenericResponse<Persona> response = personaService.findById(idPersona);
			return ResponseEntity.ok(response);
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
		}
	}

	@PostMapping("persona")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(
			@RequestHeader(name = PetardoCoreConstants.USER_EXTERNAL_HEADER, required = true) @NotBlank String usuarioExterno,
			@RequestHeader(name = PetardoCoreConstants.USER_HEADER_TOKEN, required = true) @NotBlank String usuarioToken,
			@Valid @RequestBody PersonaDto personaDto) {
		LOGGER.info("POST banco-azteca/afore/personas/v1/personas request= {}", personaDto);
		try {
			GenericResponse<Persona> response = personaService.savePersona(personaDto);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return null;
		}
	}

	@PutMapping("persona/")
	public ResponseEntity<GenericResponse<Persona>> updatePersona(
	        @RequestParam Long idPersona,
	        @RequestBody Persona personaDetails) {

	    // Verifica que personaDetails no tenga valores nulos no deseados
	    if (personaDetails == null) {
	        return new ResponseEntity<>(new GenericResponse<>(List.of("No se proporcionaron detalles para actualizar."), EnumHttpMessages.E400), HttpStatus.BAD_REQUEST);
	    }

	    GenericResponse<Persona> response = personaService.updatePersona(idPersona, personaDetails);

	    // Manejo de códigos de estado
	    HttpStatus status;
	    switch (response.getCodigo()) {
	        case EnumHttpMessages.EOK_MESSAGE:
	            status = HttpStatus.OK;
	            break;
	        case EnumHttpMessages.E404_MESSAGE:
	            status = HttpStatus.NOT_FOUND;
	            break;
	        default:
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	            break;
	    }
	     
	    return new ResponseEntity<>(response, status);
	}


	@DeleteMapping("/persona/")
	public ResponseEntity<Void> deletePersona(@RequestParam Long idPersona) {
		if (idPersona == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: el parámetro idPersona es requerido.");
		}
		try {
			personaService.deletePersona(idPersona);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se entontro persona", ex);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", ex);
		}
	}
}
