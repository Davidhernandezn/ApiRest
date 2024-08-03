package com.gs.training.petardocore.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.gs.training.petardocore.dto.PersonaDto;
import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.exception.ExceptionsManager;
import com.gs.training.petardocore.exception.GenericException;
import com.gs.training.petardocore.mapper.PersonaMapper;
import com.gs.training.petardocore.model.GenericResponse;
import com.gs.training.petardocore.model.Persona;
import com.gs.training.petardocore.repository.PersonaRepository;
import com.gs.training.petardocore.service.PersonaService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private PersonaMapper personaMapper;

	@Override
	@Transactional(readOnly = true)
	public GenericResponse<List<Persona>> getAllPersonas() {
		try {
			List<Persona> personas = personaRepository.findAll();
			return new GenericResponse<>(personas);
		} catch (Exception e) {
			List<String> detalles = List.of("Error al obtener la lista de personas", e.getMessage());
			return new GenericResponse<>(detalles, EnumHttpMessages.E400);
		}
	}

	@Override
	@Transactional
	public void deletePersona(Long idPersona) {
		Persona persona = personaRepository.findById(idPersona)
				.orElseThrow(() -> new EntityNotFoundException("No se encontro persona con id " + idPersona));
		personaRepository.delete(persona);
	}

	@Override
	@Transactional
	public GenericResponse<Persona> savePersona(PersonaDto personaDto) {
		try {
			Persona persona = personaMapper.toEntity(personaDto);
			Persona savedPersona = personaRepository.save(persona);
			return new GenericResponse<>(savedPersona);
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving persona", ex);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public GenericResponse<Persona> findById(Long idPersona) {
		try {
			Persona persona = personaRepository.findById(idPersona).orElse(null);
			if (persona == null) {
				throw new GenericException(List.of("No se obtuvo información relacionada a la consulta."),
						EnumHttpMessages.E404);
			}
			return new GenericResponse<Persona>(persona);
			// return new GenericResponse<Persona>();

		} catch (DataAccessException ex) {
			GenericException genericException = new GenericException(List.of("Error fetching persona"),
					EnumHttpMessages.E500);
			ResponseEntity<GenericResponse> errorResponse = ExceptionsManager.returnResponseEntity(genericException);
			throw new RuntimeException(errorResponse.getBody().toString(), ex);
		} catch (Exception ex) {
			GenericException genericException = new GenericException(List.of("Internal server error"),
					EnumHttpMessages.E500);
			ResponseEntity<GenericResponse> errorResponse = ExceptionsManager.returnResponseEntity(genericException);
			throw new RuntimeException(errorResponse.getBody().toString(), ex);
		}
	}

	@Override
	@Transactional
	public GenericResponse<Persona> updatePersona(Long idPersona, Persona personaDetails) {
        GenericResponse<Persona> response;

		try {
			Persona persona = personaRepository.findById(idPersona)
					.orElseThrow(() -> new EntityNotFoundException("No se encotró persona con el id: " + idPersona));

			copyNonNullProperties(personaDetails, persona);

			final Persona updatedPersona = personaRepository.save(persona);
			response = new GenericResponse<>(updatedPersona);
            response.setCodigo("200");
			//return new GenericResponse<>(updatedPersona);

		} catch (EntityNotFoundException e) {
			List<String> details = new ArrayList<>();
			details.add(e.getMessage());
			return new GenericResponse<>(details, EnumHttpMessages.E404);
		} catch (Exception e) {
			List<String> details = new ArrayList<>();
			details.add("An error occurred while updating the persona.");
			return new GenericResponse<>(details, EnumHttpMessages.E500);
		}
		return response;
	}

	private void copyNonNullProperties(Object source, Object target) {
		BeanUtilsBean notNull = new BeanUtilsBean() {
			@Override
			public void copyProperty(Object dest, String name, Object value)
					throws IllegalAccessException, InvocationTargetException {
				if (value != null) {
					super.copyProperty(dest, name, value);
				}
			}
		};
		try {
			notNull.copyProperties(target, source);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// Maneja las excepciones adecuadamente, podrías relanzarlas como
			// RuntimeException si es necesario
			throw new RuntimeException("Error copying properties", e);
		}
	}
}
