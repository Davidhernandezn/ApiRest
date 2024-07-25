package com.gs.training.petardocore.controller;

import com.gs.training.petardocore.dto.PersonaDto;
import com.gs.training.petardocore.mapper.PersonaMapper;
import com.gs.training.petardocore.model.Persona;
import com.gs.training.petardocore.service.PersonaService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${basePath}")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaMapper personaMapper;

    @GetMapping("/personas")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonaDto> getAllPersonas() {
        return personaService.getAllPersonas().stream()
                .map(personaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/persona/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Persona persona = personaService.findById(id);
        if (persona == null) {
            return new ResponseEntity<>(createErrorResponse("Error: la persona ID: " + id + " no existe."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personaMapper.toDTO(persona), HttpStatus.OK);
    }

    @PostMapping("/persona")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody PersonaDto personaDTO) {
        try {
            Persona persona = personaMapper.toEntity(personaDTO);
            Persona savedPersona = personaService.savePersona(persona);
            return new ResponseEntity<>(personaMapper.toDTO(savedPersona), HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(createErrorResponse("Error al realizar la inserción en la base de datos.", exDt), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/persona/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updatePerson(@RequestBody PersonaDto personaDTO,@PathVariable Long id) {
        //Persona personaActual = personaService.findById(personaDTO.getId());
        Persona personaFindById = personaService.findById(id);
        if (personaFindById == null) {
            return new ResponseEntity<>(createErrorResponse("Error: no se pudo editar, la persona ID: " + personaDTO.getId() + " no existe."), HttpStatus.NOT_FOUND);
        }

        try {
            personaDTO.setId(id);
            BeanUtils.copyProperties(personaFindById, personaDTO);
            Persona updatedPersona = personaService.savePersona(personaFindById);
            return new ResponseEntity<>(personaMapper.toDTO(updatedPersona), HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(createErrorResponse("Error al actualizar la persona en la base de datos.", exDt), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(createErrorResponse("Error al copiar las propiedades de la persona.", e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Persona personaDelete = personaService.findById(id);
            if (personaDelete == null) {
                return new ResponseEntity<>(createErrorResponse("Error: no se pudo eliminar, la persona ID: " + id + " no existe."), HttpStatus.NOT_FOUND);
            }

            personaService.deletePersona(personaDelete);
            return new ResponseEntity<>(createSuccessResponse("La persona fue eliminada con éxito."), HttpStatus.OK);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(createErrorResponse("Error al realizar la eliminación en la base de datos.", exDt), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", message);
        return response;
    }

    private Map<String, Object> createErrorResponse(String message, Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", message);
        response.put("error", ex.getMessage().concat(": ").concat(ex.getCause() != null ? ex.getCause().getMessage() : ""));
        return response;
    }

    private Map<String, Object> createSuccessResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", message);
        return response;
    }
}
