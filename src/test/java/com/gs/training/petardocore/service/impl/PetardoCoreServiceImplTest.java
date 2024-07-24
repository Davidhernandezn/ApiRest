package com.gs.training.petardocore.service.impl;

import com.gs.training.petardocore.dto.PetardoCoreDto;
import com.gs.training.petardocore.dto.registry.PetardoCoreRequestRegistry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PetardoCoreServiceImplTest {

	@InjectMocks
	private PetardoCoreServiceImpl service;

	@Test
	void testConsultPetardoCore() {

		List<PetardoCoreDto> expected = Collections.singletonList(new PetardoCoreDto(1L, "CoE Microservices"));
		var result = service.consultPetardoCore("1");
		assertEquals(expected, result, "equals");
	}

	@Test
	void testCreatePetardoCore() {

		var item = new PetardoCoreDto(1L, "CoE Microservicios");
		var request = new PetardoCoreRequestRegistry("1", "CoE Microservicios");
		List<PetardoCoreDto> expected = Collections.singletonList(item);
		var result = service.createPetardoCore(request);
		assertEquals(expected, result, "equals");
	}

	@Test
	void testConsultLoansIdError2() {

		try {

			service.consultPetardoCore("a");
		} catch (RuntimeException ex) {

			assertEquals("Request Error", ex.getMessage());
		}
	}

	@Test
	void testCreateLoansIdError2() {

		try {

			PetardoCoreRequestRegistry demoRequestRegistry = new PetardoCoreRequestRegistry();

			demoRequestRegistry.setIdPetardoCore("a");
			demoRequestRegistry.setName("name");

			service.createPetardoCore(demoRequestRegistry);
		} catch (RuntimeException ex) {

			assertEquals("Request Error", ex.getMessage());
		}
	}
}
