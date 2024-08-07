package com.gs.training.petardocore.service;

import java.util.List;
import com.gs.training.petardocore.dto.PersonaDto;
import com.gs.training.petardocore.model.GenericResponse;
import com.gs.training.petardocore.model.Persona;

public interface PersonaService {

	/**
	 * Consultar personas
	 * 
	 * @return lista de todas las Personas.
	 */
	GenericResponse<List<Persona>> getAllPersonas();

	/**
	 * Busca a una persona por su id
	 * 
	 * @param Long idPersona
	 * @return persona consultada por su id.
	 */
	GenericResponse<Persona> findById(Long idPersona);

	/**
	 * Guarda registro de una persona
	 * 
	 * @param personaDto que contiene datos de una persona a guardar.
	 * @return persona guardada.
	 */
	GenericResponse<Persona> savePersona(PersonaDto personaDto);

	/**
	 * Eliminar a una persona por su id
	 * 
	 * @param Long idPersona
	 */
	void deletePersona(Long idPersona);

	/**
	 * Actualiza el registro de una presona
	 * 
	 * @param idPersona      de la Persona que se desea actualizar.
	 * @param personaDetails los datos modificados para Persona.
	 * @return Persona actualizada.
	 */
	GenericResponse<Persona> updatePersona(Long idPersona, Persona personaDetails);
}