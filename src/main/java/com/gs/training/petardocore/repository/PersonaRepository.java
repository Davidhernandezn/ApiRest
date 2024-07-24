package com.gs.training.petardocore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gs.training.petardocore.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>{

}
