package com.gs.training.petardocore.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;
import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.model.GenericResponse;

class ExceptionsManagerTest
{
	@Test
	void testReturnResponseEntity()
	{
		GenericException genericExceptionMock = mock(GenericException.class);
		when(genericExceptionMock.getDetails()).thenReturn(Collections.singletonList("Test error"));
		when(genericExceptionMock.getEnumHttpMessages()).thenReturn(EnumHttpMessages.E400);

		ResponseEntity<GenericResponse> responseEntity = ExceptionsManager.returnResponseEntity(genericExceptionMock);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

		GenericResponse response = responseEntity.getBody();
		assertEquals("Test error", response.getDetails().get(0));
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#parseResponseStatusExceptionToGenericException(org.springframework.web.server.ResponseStatusException)}
	 */
	@Test
	void testParseResponseStatusExceptionToGenericExceptionBAD_REQUEST()
	{
		GenericResponse genericException = new GenericResponse(Collections.singletonList("An error"),
				EnumHttpMessages.E400);
		ResponseStatusException exception = new ResponseStatusException(HttpStatus.BAD_REQUEST,
				new Gson().toJson(genericException));

		GenericException result = ExceptionsManager.parseResponseStatusExceptionToGenericException(exception);

		assertEquals(EnumHttpMessages.E400, result.getEnumHttpMessages());
		assertEquals("An error", result.getDetails().get(0));
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#parseResponseStatusExceptionToGenericException(org.springframework.web.server.ResponseStatusException)}
	 */
	@Test
	void testParseResponseStatusExceptionToGenericExceptionUNAUTHORIZED()
	{
		GenericResponse genericException = new GenericResponse(Collections.singletonList("An error"),
				EnumHttpMessages.E401);
		ResponseStatusException exception = new ResponseStatusException(HttpStatus.UNAUTHORIZED,
				new Gson().toJson(genericException));

		GenericException result = ExceptionsManager.parseResponseStatusExceptionToGenericException(exception);

		assertEquals(EnumHttpMessages.E401, result.getEnumHttpMessages());
		assertEquals("An error", result.getDetails().get(0));
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#parseResponseStatusExceptionToGenericException(org.springframework.web.server.ResponseStatusException)}
	 */
	@Test
	void testParseResponseStatusExceptionToGenericExceptionNOT_FOUND()
	{
		GenericResponse genericException = new GenericResponse(Collections.singletonList("An error"),
				EnumHttpMessages.E404);
		ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND,
				new Gson().toJson(genericException));

		GenericException result = ExceptionsManager.parseResponseStatusExceptionToGenericException(exception);

		assertEquals(EnumHttpMessages.E404, result.getEnumHttpMessages());
		assertEquals("An error", result.getDetails().get(0));
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#parseResponseStatusExceptionToGenericException(org.springframework.web.server.ResponseStatusException)}
	 */
	@Test
	void testParseResponseStatusExceptionToGenericExceptionINTERNAL_SERVER_ERROR()
	{
		GenericResponse genericException = new GenericResponse(Collections.singletonList("An error"),
				EnumHttpMessages.E500);
		ResponseStatusException exception = new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
				new Gson().toJson(genericException));

		GenericException result = ExceptionsManager.parseResponseStatusExceptionToGenericException(exception);

		assertEquals(EnumHttpMessages.E500, result.getEnumHttpMessages());
		assertEquals("An error", result.getDetails().get(0));
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#parseResponseStatusExceptionToGenericException(org.springframework.web.server.ResponseStatusException)}
	 */
	@Test
	void testParseResponseStatusExceptionToExcepcionGenerica2()
	{
		GenericException currentParseResponseStatusExceptionAExceptionGenericResult = ExceptionsManager
				.parseResponseStatusExceptionToGenericException(null);
		assertEquals(EnumHttpMessages.E500,
				currentParseResponseStatusExceptionAExceptionGenericResult.getEnumHttpMessages());
		assertEquals(1, currentParseResponseStatusExceptionAExceptionGenericResult.getDetails().size());
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#parseResponseStatusExceptionToGenericException(org.springframework.web.server.ResponseStatusException)}
	 */
	@Test
	void testParseResponseStatusExceptionToGenericException3()
	{
		GenericException currentParseResponseStatusExceptionToGenericExceptionResult = ExceptionsManager
				.parseResponseStatusExceptionToGenericException(
						new ResponseStatusException(HttpStatus.CONTINUE, "Just cause"));
		assertEquals(EnumHttpMessages.E500,
				currentParseResponseStatusExceptionToGenericExceptionResult.getEnumHttpMessages());
		assertEquals(1, currentParseResponseStatusExceptionToGenericExceptionResult.getDetails().size());
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#parseResponseStatusExceptionToGenericException(org.springframework.web.server.ResponseStatusException)}
	 */
	@Test
	void testParseResponseStatusExceptionToGenericException5()
	{
		GenericException currentParseResponseStatusExceptionToGenericExceptionResult = ExceptionsManager
				.parseResponseStatusExceptionToGenericException(
						new ResponseStatusException(HttpStatus.CONTINUE, "Error"));
		assertEquals(EnumHttpMessages.E500,
				currentParseResponseStatusExceptionToGenericExceptionResult.getEnumHttpMessages());
		assertEquals(1, currentParseResponseStatusExceptionToGenericExceptionResult.getDetails().size());
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#parseResponseStatusExceptionToGenericException(org.springframework.web.server.ResponseStatusException)}
	 */
	@Test
	void testParseResponseStatusExceptionToGenericException6()
	{
		GenericException currentParseResponseStatusExceptionToGenericExceptionResult = ExceptionsManager
				.parseResponseStatusExceptionToGenericException(
						new ResponseStatusException(HttpStatus.CONTINUE, "totalTime"));
		assertEquals(EnumHttpMessages.E500,
				currentParseResponseStatusExceptionToGenericExceptionResult.getEnumHttpMessages());
		assertEquals(1, currentParseResponseStatusExceptionToGenericExceptionResult.getDetails().size());
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#parseResponseStatusExceptionToGenericException(org.springframework.web.server.ResponseStatusException)}
	 */
	@Test
	void testParseResponseStatusExceptionToGenericException7()
	{
		GenericException currentParseResponseStatusExceptionToGenericExceptionResult = ExceptionsManager
				.parseResponseStatusExceptionToGenericException(new ResponseStatusException(HttpStatus.CONTINUE, "42"));
		assertEquals(EnumHttpMessages.E500,
				currentParseResponseStatusExceptionToGenericExceptionResult.getEnumHttpMessages());
		assertEquals(1, currentParseResponseStatusExceptionToGenericExceptionResult.getDetails().size());
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#parseResponseStatusExceptionToGenericException(org.springframework.web.server.ResponseStatusException)}
	 */
	@Test
	void testParseResponseStatusExceptionToGenericException8()
	{
		GenericException currentParseResponseStatusExceptionToGenericExceptionResult = ExceptionsManager
				.parseResponseStatusExceptionToGenericException(new ResponseStatusException(HttpStatus.CONTINUE, ""));
		assertEquals(EnumHttpMessages.E500,
				currentParseResponseStatusExceptionToGenericExceptionResult.getEnumHttpMessages());
		assertEquals(1, currentParseResponseStatusExceptionToGenericExceptionResult.getDetails().size());
	}

	@Test
	void testRetornarExcepcionGenerica()
	{
		GenericException currentReturnGenericExceptionResult = ExceptionsManager
				.returnGenericException(new Exception("foo"));
		assertEquals(EnumHttpMessages.E500, currentReturnGenericExceptionResult.getEnumHttpMessages());
		assertEquals(1, currentReturnGenericExceptionResult.getDetails().size());
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#returnGenericException(Exception)}
	 */
	@Test
	void testReturnGenericExceptionEXCEPCIONGENERICA()
	{
		GenericException currentReturnGenericExceptionResult = ExceptionsManager
				.returnGenericException(new GenericException(Collections.singletonList("foo"), EnumHttpMessages.E500));
		assertEquals(EnumHttpMessages.E500, currentReturnGenericExceptionResult.getEnumHttpMessages());
		assertEquals(1, currentReturnGenericExceptionResult.getDetails().size());
	}

	/**
	 * Method under test:
	 * {@link ExceptionsManager#retornarExcepcionGenerica(Exception)}
	 */
	@Test
	void testRetornarExcepcionGenericaRESPONSESTATUSEXCEPTION()
	{
		GenericResponse genericException = new GenericResponse(Collections.singletonList("An error"),
				EnumHttpMessages.E400);
		ResponseStatusException exception = new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
				new Gson().toJson(genericException));
		GenericException currentReturnGenericExceptionResult = ExceptionsManager.returnGenericException(exception);
		assertEquals(EnumHttpMessages.E500, currentReturnGenericExceptionResult.getEnumHttpMessages());
	}
}
