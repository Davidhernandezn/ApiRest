package com.gs.training.petardocore.util;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gs.training.petardocore.dto.PetardoCoreDto;

class ConvertersTest {

	@Test
	void testJson() throws JsonProcessingException, JSONException {
		
		PetardoCoreDto dto = new PetardoCoreDto();
		dto.setIdPersona(1L);
		dto.setName("Coe");

		Converters converters = new Converters();
		String result = converters.convertObjectToJson(dto);

		assertFalse(result.isEmpty());
	}

	@Test
	void testNullObject() throws JsonProcessingException {
		
		Converters converters = new Converters();
		assertNull(converters.convertObjectToJson(null));
	}
}
