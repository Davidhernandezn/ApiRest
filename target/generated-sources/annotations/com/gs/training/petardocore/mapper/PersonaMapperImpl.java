package com.gs.training.petardocore.mapper;

import com.gs.training.petardocore.dto.PersonaDto;
import com.gs.training.petardocore.model.Persona;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-05T18:41:31-0600",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class PersonaMapperImpl implements PersonaMapper {

    @Override
    public PersonaDto toDTO(Persona persona) {
        if ( persona == null ) {
            return null;
        }

        PersonaDto personaDto = new PersonaDto();

        personaDto.setApellidoMaterno( persona.getApellidoMaterno() );
        personaDto.setApellidoPaterno( persona.getApellidoPaterno() );
        personaDto.setCiudad( persona.getCiudad() );
        personaDto.setEdad( persona.getEdad() );
        personaDto.setEmail( persona.getEmail() );
        personaDto.setIdPersona( persona.getIdPersona() );
        personaDto.setNombre( persona.getNombre() );
        personaDto.setTelefono( persona.getTelefono() );

        return personaDto;
    }

    @Override
    public Persona toEntity(PersonaDto personaDTO) {
        if ( personaDTO == null ) {
            return null;
        }

        Persona persona = new Persona();

        persona.setApellidoMaterno( personaDTO.getApellidoMaterno() );
        persona.setApellidoPaterno( personaDTO.getApellidoPaterno() );
        persona.setCiudad( personaDTO.getCiudad() );
        if ( personaDTO.getEdad() != null ) {
            persona.setEdad( personaDTO.getEdad() );
        }
        persona.setEmail( personaDTO.getEmail() );
        persona.setIdPersona( personaDTO.getIdPersona() );
        persona.setNombre( personaDTO.getNombre() );
        persona.setTelefono( personaDTO.getTelefono() );

        return persona;
    }
}
