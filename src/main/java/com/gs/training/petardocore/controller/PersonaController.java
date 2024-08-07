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
import java.util.stream.Collectors;

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
		GenericResponse<List<Persona>> response = personaService.getAllPersonas();

		List<PersonaDto> personaDtos = response.getResultado().stream().map(personaMapper::toDTO)
				.collect(Collectors.toList());

		GenericResponse<List<PersonaDto>> dtoResponse = new GenericResponse<>();
		dtoResponse.setCodigo(response.getCodigo());
		dtoResponse.setMensaje(response.getMensaje());
		dtoResponse.setFolio(response.getFolio());
		dtoResponse.setInfo(response.getInfo());
		dtoResponse.setDetalles(response.getDetalles());
		dtoResponse.setResultado(personaDtos);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
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
	public ResponseEntity<GenericResponse<Persona>> updatePersona(@RequestParam Long idPersona,
			@RequestBody Persona personaDetails) {
		GenericResponse<Persona> response = personaService.updatePersona(idPersona, personaDetails);

		if (response.getCodigo().equals(EnumHttpMessages.EOK_MESSAGE)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else if (response.getCodigo().equals(EnumHttpMessages.E404_MESSAGE)) {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
