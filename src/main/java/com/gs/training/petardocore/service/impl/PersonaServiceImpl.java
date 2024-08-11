package com.gs.training.petardocore.service.impl;

import java.lang.System.Logger;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.gs.training.petardocore.dto.PersonaDto;
import com.gs.training.petardocore.enums.EnumHttpMessages;
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
	public GenericResponse<List<PersonaDto>> getAllPersonas() {
		try {
			List<Persona> personas = personaRepository.findAll();
			List<PersonaDto> personaDtos = personas.stream().map(personaMapper::toDTO).collect(Collectors.toList());
			return new GenericResponse<>(personaDtos, EnumHttpMessages.M200);
		} catch (RuntimeException e) {
			return new GenericResponse<>(List.of("Error al obtener la lista de personas", e.getMessage()),
					EnumHttpMessages.E400);
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
			return new GenericResponse<>(savedPersona, EnumHttpMessages.M201);
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving persona", ex);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public GenericResponse<Persona> findById(Long idPersona) {
		try {
			return personaRepository.findById(idPersona)
					.map(persona -> new GenericResponse<>(persona, EnumHttpMessages.M200)).orElseGet(
							() -> new GenericResponse<>(List.of("No se obtuvo información relacionada a la consulta."),
									EnumHttpMessages.E404));
		} catch (DataAccessException ex) {
			return new GenericResponse<>(List.of("Error al buscar a la persona", ex.getMessage()),
					EnumHttpMessages.E500);
		} catch (Exception ex) {
			return new GenericResponse<>(List.of("Internal server error", ex.getMessage()), EnumHttpMessages.E500);
		}
	}

	@Override
	@Transactional
	public GenericResponse<Persona> updatePersona(Long idPersona, Persona personaDetails) {
		Persona persona = personaRepository.findById(idPersona).orElse(null);

		if (persona == null) {
			return new GenericResponse<>(List.of("No se encontró persona con el id: " + idPersona),
					EnumHttpMessages.E404);
		}

		try {
			copyNonNullProperties(personaDetails, persona, 0);
			Persona updatedPersona = personaRepository.save(persona);
			return new GenericResponse<>(updatedPersona, EnumHttpMessages.M200);
		} catch (Exception e) {
			return new GenericResponse<>(List.of("Error al actualizar persona: " + e.getMessage()),
					EnumHttpMessages.E500);
		}
	}

	private void copyNonNullProperties(Persona source, Persona target, int defaultValue) {

		BeanUtilsBean notNull = new BeanUtilsBean() {
			@Override
			public void copyProperty(Object dest, String name, Object value)
					throws IllegalAccessException, InvocationTargetException {
				if (value != null && !(value instanceof Integer && (Integer) value == defaultValue)) {
					super.copyProperty(dest, name, value);
				}
			}
		};
		try {
			notNull.copyProperties(target, source);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("Error copying properties", e);
		}
	}

}
