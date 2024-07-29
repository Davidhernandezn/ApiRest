package com.gs.training.petardocore.service;

import java.util.List;
import com.gs.training.petardocore.model.Persona;

public interface PersonaService {

    /**
     * Retrieves all Persona entities.
     * @return a List of all Persona.
     */
    List<Persona> getAllPersonas();
    
    /**
     * Finds a Persona by ID.
     * @param Long id 
     * @return the Persona with the specified ID.
     */
    Persona findById(Long id);

    /**
     * Saves a new Person. 
     * @param persona the Persona to saved.
     * @return the saved Persona.
     */
    Persona savePersona(Persona persona);

    /**
     * Deletes an existing Persona. 
     * @param Long id 
     */
    void deletePersona(Long id);

    
    /**
     * Updates an existing Persona.
     * @param id the ID of the Persona to be updated.
     * @param personaDetails the new details for the Persona.
     * @return the updated Persona.
     */
    Persona updatePersona(Long id, Persona personaDetails);
}