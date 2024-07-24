package com.gs.training.petardocore.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class FolioTest {

	private Folio folio = new Folio();
	private String headerFolio = "a24";

	@Test
	void testGetFolioWithIdMSO() {
		
		String response = folio.getFolioWithIdMSO();
		assertTrue(response.matches("^[null|0-9-]+$"));
	}

	@Test
	void testGenerateFolio() {
		
		String response2 = folio.generateFolio(headerFolio);
		assertTrue(response2.matches("^[a-z0-9-]+$"));
	}

	@Test
	void testGenerateFolio2() {
		
		String response = folio.generateFolio(null);
		assertTrue(response.matches("^[null|0-9-]+$"));

		String response2 = folio.generateFolio("");
		assertTrue(response2.matches("^[null|0-9-]+$"));

		String response3 = folio.generateFolio(" ");
		assertTrue(response3.matches("^[null|0-9-]+$"));
	}

	@Test
	void testSaveFolio() {
		
		Map<String, String> headers = new HashMap<>();
		Map<String, String> response = folio.saveFolio(headers);
		assertTrue(response.get("x-id-interaction").matches("^[null|0-9-]+$"));
	}

	@Test
	void testSaveFolio2() {
		
		Map<String, String> headers = new HashMap<>();
		headers.put("x-id-interaction", "1");
		Map<String, String> response = folio.saveFolio(headers);
		assertEquals(response.get("x-id-interaction"), headerFolio);
	}

	@Test
	void testCleanFolio() {
		
		folio.cleanFolio();
		assertTrue(true);
	}
}
