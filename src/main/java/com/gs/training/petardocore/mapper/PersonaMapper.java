package com.gs.training.petardocore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.gs.training.petardocore.dto.PersonaDto;
import com.gs.training.petardocore.model.Persona;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
	   PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);
	   PersonaDto toDTO(Persona persona);
	   Persona toEntity(PersonaDto personaDTO);
}
