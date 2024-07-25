package com.gs.training.petardocore.service;

import java.util.List;
import java.util.Optional;

import com.gs.training.petardocore.model.Persona;

public interface PersonaService {

    List<Persona> getAllPersonas();

    Persona findById(Long id);

    Persona savePersona(Persona persona);

    void deletePersona(Persona persona);
    
    Persona updatePersona(Long id, Persona personaDetails);
}