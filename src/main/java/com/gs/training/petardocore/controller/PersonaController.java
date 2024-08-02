package com.gs.training.petardocore.controller;
import com.gs.ftt.log.LogMonitor;
import com.gs.training.petardocore.constant.PetardoCoreConstants;
import com.gs.training.petardocore.dto.PersonaDto;
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
	public List<PersonaDto> getAllPersonas() {
		return personaService.getAllPersonas().stream().map(personaMapper::toDTO).collect(Collectors.toList());
	}

	@GetMapping("/persona/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponse> findById(@RequestParam Long id) {
		try {
			GenericResponse<Persona> response = personaService.findById(id);
			return ResponseEntity.ok(response);
		} catch (RuntimeException ex) {
			// Manejar excepciones lanzadas desde el servicio
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

	@DeleteMapping("/persona/")
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
}
