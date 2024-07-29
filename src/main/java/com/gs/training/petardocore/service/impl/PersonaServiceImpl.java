package com.gs.training.petardocore.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gs.training.petardocore.model.Persona;
import com.gs.training.petardocore.repository.PersonaRepository;
import com.gs.training.petardocore.service.PersonaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
	@Transactional(readOnly = true)
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }


    @Override
	@Transactional
    public Persona savePersona(Persona persona) {
        return personaRepository.save(persona);
    }


    @Override
   	@Transactional
	public void deletePersona(Long id) {
		 Persona persona = personaRepository.findById(id)
		                .orElseThrow(() -> new EntityNotFoundException("Persona not found with id " + id));
		        personaRepository.delete(persona);
	}
	
    @Override
	@Transactional
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


	@Override
	@Transactional(readOnly = true)
	public Persona findById(Long id) {
		// TODO Auto-generated method stub
		return personaRepository.findById(id).orElse(null);
	}

}
