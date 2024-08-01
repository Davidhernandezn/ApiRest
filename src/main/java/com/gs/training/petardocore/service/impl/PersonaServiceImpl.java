package com.gs.training.petardocore.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.gs.training.petardocore.dto.PersonaDto;
import com.gs.training.petardocore.exception.ExceptionsManager;
import com.gs.training.petardocore.exception.GenericException;
import com.gs.training.petardocore.mapper.PersonaMapper;
import com.gs.training.petardocore.model.CommonResponse;
import com.gs.training.petardocore.model.GenericResponse;
import com.gs.training.petardocore.model.Persona;
import com.gs.training.petardocore.repository.PersonaRepository;
import com.gs.training.petardocore.service.PersonaService;

import jakarta.persistence.EntityNotFoundException;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;
    
	//@Autowired
	//private PersonaService personaService;

	@Autowired
	private PersonaMapper personaMapper;

    @Override
	@Transactional(readOnly = true)
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }  
    
    @Override
    @Transactional
    public CommonResponse<Persona> savePersona(PersonaDto personaDto) {
        try {
            // Convertir el DTO a la entidad
            Persona persona = personaMapper.toEntity(personaDto);
            // Guardar la entidad
            Persona savedPersona = personaRepository.save(persona);
            // Crear y devolver la respuesta con mensaje
            return new CommonResponse<>("Operación exitosa", savedPersona);
        } catch (DataAccessException ex) {
            // Manejar excepción y devolver respuesta con mensaje de error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving persona", ex);
        }
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
	public CommonResponse<Persona> findById(Long id) {
	    try {
	        Persona persona = personaRepository.findById(id).orElse(null);
	        if (persona == null) {
	            return new CommonResponse<>("Persona no encontrada", null);
	        }
	        return new CommonResponse<>("Operación exitosa", persona);
	    } catch (DataAccessException ex) {
	        CommonResponse<Persona> errorResponse = new CommonResponse<>("Error fetching persona", null);
	        // Lanzar una ResponseStatusException con la respuesta de error
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse.toString(), ex);
	    }

	}}
