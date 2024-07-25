package com.gs.training.petardocore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.gs.training.petardocore.model.Persona;
import com.gs.training.petardocore.service.PersonaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("${basePath}")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/personas")
    @ResponseStatus(HttpStatus.OK)
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }
    
    @GetMapping("/persona/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Persona showById(@PathVariable Long id) {
        return personaService.findById(id);
    }
    
    @PostMapping("/persona")
    @ResponseStatus(HttpStatus.CREATED)
    public Persona create(@RequestBody Persona persona) {
        return personaService.savePersona(persona);
    }
    
    @PutMapping("/persona")
    @ResponseStatus(HttpStatus.CREATED)
    public Persona updatePerson(@RequestBody Persona persona) {
        return personaService.savePersona(persona);
    }
    
    @DeleteMapping("/persona/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) { 
        Map<String, Object> response = new HashMap<>();
        
        try {
            Persona personaDelete = personaService.findById(id);
            
            if (personaDelete == null) {
                response.put("mensaje", "Error: no se pudo eliminar, la persona ID: ".concat(id.toString()).concat(" no existe."));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            
            personaService.deletePersona(personaDelete);
            response.put("mensaje", "La persona fue eliminada con éxito.");
            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (DataAccessException exDt) {
            response.put("mensaje", "Error al realizar la eliminación en la base de datos.");
            response.put("error", exDt.getMessage().concat(": ").concat(exDt.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}