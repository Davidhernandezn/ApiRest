package com.gs.training.petardocore.service;

import java.util.List;
import com.gs.training.petardocore.dto.PersonaDto;
import com.gs.training.petardocore.model.GenericResponse;
import com.gs.training.petardocore.model.Persona;

public interface PersonaService {

	/**
	 * Retrieves all Persona entities.
	 * 
	 * @return a List of all Persona.
	 */
	GenericResponse<List<Persona>> getAllPersonas();

	/**
	 * Finds a Persona by ID.
	 * 
	 * @param Long id
	 * @return the Persona with the specified ID.
	 */
	GenericResponse<Persona> findById(Long idPersona);

	/**
	 * Saves a new Person
	 * 
	 * @param persona the Persona to saved.
	 * @return the saved Persona.
	 */
	GenericResponse<Persona> savePersona(PersonaDto personaDto);

	/**
	 * Deletes an existing Persona.
	 * 
	 * @param Long id
	 */
	void deletePersona(Long idPersona);

	/**
	 * Updates an existing Persona.
	 * 
	 * @param id             the ID of the Persona to be updated.
	 * @param personaDetails the new details for the Persona.
	 * @return the updated Persona.
	 */
	//Persona updatePersona(Long idPersona, Persona personaDetails);
    GenericResponse<Persona> updatePersona(Long idPersona, Persona personaDetails);
}