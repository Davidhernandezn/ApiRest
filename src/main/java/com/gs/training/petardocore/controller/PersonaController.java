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

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        return personaService.getPersonaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.savePersona(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        return personaService.getPersonaById(id)
                .map(persona -> ResponseEntity.ok(personaService.updatePersona(id, personaDetails)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePersona(@PathVariable Long id) {
        return personaService.getPersonaById(id)
                .map(persona -> {
                    personaService.deletePersona(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}