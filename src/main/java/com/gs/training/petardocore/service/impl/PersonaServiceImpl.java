package com.gs.training.petardocore.service.impl;

import java.util.List;
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
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
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
	public GenericResponse<Persona> findById(Long id) {
        try {
            Persona persona = personaRepository.findById(id).orElse(null);
            if (persona == null) {
                throw new GenericException(
                    List.of("Persona no encontrada :/"),
                    EnumHttpMessages.E404
                );
            }
            return new GenericResponse<Persona>(persona);
            //return new GenericResponse<Persona>();

        } catch (DataAccessException ex) {
            GenericException genericException = new GenericException(
                List.of("Error fetching persona"),
                EnumHttpMessages.E500
            );
            ResponseEntity<GenericResponse> errorResponse = ExceptionsManager.returnResponseEntity(genericException);
            throw new RuntimeException(errorResponse.getBody().toString(), ex);
        } catch (Exception ex) {
            GenericException genericException = new GenericException(
                List.of("Internal server error"),
                EnumHttpMessages.E500
            );
            ResponseEntity<GenericResponse> errorResponse = ExceptionsManager.returnResponseEntity(genericException);
            throw new RuntimeException(errorResponse.getBody().toString(), ex);
        }
    }
	
}
