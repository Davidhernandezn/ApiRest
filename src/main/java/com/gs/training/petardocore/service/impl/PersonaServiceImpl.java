package com.gs.training.petardocore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gs.training.petardocore.model.Persona;
import com.gs.training.petardocore.repository.PersonaRepository;
import com.gs.training.petardocore.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> getPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public Persona savePersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona updatePersona(Long id, Persona personaDetails) {
        return personaRepository.findById(id).map(persona -> {
            persona.setNombre(personaDetails.getNombre());
            persona.setApellidoMaterno(personaDetails.getApellidoMaterno());
            persona.setApellidoPaterno(personaDetails.getApellidoPaterno());
            return personaRepository.save(persona);
        }).orElseGet(() -> {
            personaDetails.setId(id);
            return personaRepository.save(personaDetails);
        });
    }
}
