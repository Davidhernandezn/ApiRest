package com.gs.training.petardocore.controller;

import com.gs.training.petardocore.dto.PetardoCoreDto;
import com.gs.training.petardocore.dto.registry.PetardoCoreRequestRegistry;
import com.gs.training.petardocore.model.GenericResponse;
import com.gs.training.petardocore.service.IPetardoCoreService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetardoCoreControllerTest {
	
	@InjectMocks
	private PetardoCoreController petardocoreController;

	@Mock
	private IPetardoCoreService iPetardoCoreService;

	@Test
	void testConsult() {
		
		String id = "1";
		when(iPetardoCoreService.consultPetardoCore(id))
				.thenReturn(Collections.singletonList(new PetardoCoreDto(1L, "CoE Microservices")));

		GenericResponse genericResponse = petardocoreController.consultPetardoCore(Collections.emptyMap(), id);
		var toCompare = new GenericResponse(Collections.singletonList(new PetardoCoreDto(1L, "CoE Microservices")));

		assertEquals(genericResponse, toCompare, "equals");
	}

	@Test
	void testConsultPetardoCore() {
		
		PetardoCoreRequestRegistry requestRegistry = new PetardoCoreRequestRegistry("1", "name");

		when(iPetardoCoreService.createPetardoCore(requestRegistry)).thenReturn(
				Collections.singletonList(new PetardoCoreDto(Long.valueOf(requestRegistry.getIdPetardoCore()), requestRegistry.getName())));

		GenericResponse genericResponse = petardocoreController.createPetardoCore(requestRegistry);
		var toCompare = new GenericResponse(Collections.singletonList(new PetardoCoreDto(1L, "name")));

		assertEquals(genericResponse, toCompare, "equals");
	}
}
