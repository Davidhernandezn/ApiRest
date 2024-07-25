package com.gs.training.petardocore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.training.petardocore.model.Persona;
import com.gs.training.petardocore.service.PersonaService;
import java.util.List;


@RestController
@RequestMapping("${basePath}")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/personas")
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }
    
    @GetMapping("/persona/{id}")
    public Persona showById(@PathVariable Long id) {
        return personaService.findById(id);
    }
    
    @PostMapping("/persona")
    public Persona create(@RequestBody Persona persona) {
        return personaService.savePersona(persona);
    }
    
    @PutMapping("/persona")
    public Persona updatePerson(@RequestBody Persona persona) {
        return personaService.savePersona(persona);
    }
    
    @DeleteMapping("/persona/{id}")
    public void delete(@PathVariable Long id) {
    	Persona personaDelete = personaService.findById(id);
        personaService.deletePersona(id);
    }
}