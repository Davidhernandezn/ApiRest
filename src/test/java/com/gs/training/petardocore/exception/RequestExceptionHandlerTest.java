package com.gs.training.petardocore.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.DataInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gs.training.petardocore.model.GenericResponse;

class RequestExceptionHandlerTest
{
	private RequestExceptionHandler requestExcepcion;

	@Mock
	private ExceptionsManager exceptionsManager;

	@BeforeEach
	void setUp() throws Exception
	{
		requestExcepcion = new RequestExceptionHandler();
	}

	@Test
	void testHandleValidationRequestParameterException()
	{
		ResponseEntity<GenericResponse> actualHandleValidationRequestParameterExceptionResult = requestExcepcion
				.handleValidationRequestParameterException(
						new MissingServletRequestParameterException("Parameter Name", "Parameter Type"));
		assertTrue(actualHandleValidationRequestParameterExceptionResult.hasBody());
		assertTrue(actualHandleValidationRequestParameterExceptionResult.getHeaders().isEmpty());
		assertEquals(HttpStatus.BAD_REQUEST, actualHandleValidationRequestParameterExceptionResult.getStatusCode());
		GenericResponse body = actualHandleValidationRequestParameterExceptionResult.getBody();
		assertEquals(1, body.getDetails().size());
		assertEquals("Invalid parameters, please validate your information.", body.getMessage());
	}

	@Test
	void testHandleValidationFormat()
	{
		ResponseEntity<GenericResponse> actualHandleValidationFormatResult = requestExcepcion
				.handleValidationFormat(new HttpMessageNotReadableException("https://example.org/example"));
		assertTrue(actualHandleValidationFormatResult.hasBody());
		assertTrue(actualHandleValidationFormatResult.getHeaders().isEmpty());
		assertEquals(HttpStatus.BAD_REQUEST, actualHandleValidationFormatResult.getStatusCode());
		GenericResponse body = actualHandleValidationFormatResult.getBody();
		assertEquals(1, body.getDetails().size());
		assertEquals("Invalid parameters, please validate your information.", body.getMessage());
	}

	@Test
	void testHandleValidationFormat2()
	{
		ResponseEntity<GenericResponse> actualHandleValidationFormatResult = requestExcepcion
				.handleValidationFormat(new HttpMessageNotReadableException("https://example.org/example",
						new MockHttpInputMessage(mock(DataInputStream.class))));
		assertTrue(actualHandleValidationFormatResult.hasBody());
		assertTrue(actualHandleValidationFormatResult.getHeaders().isEmpty());
		assertEquals(HttpStatus.BAD_REQUEST, actualHandleValidationFormatResult.getStatusCode());
		GenericResponse body = actualHandleValidationFormatResult.getBody();
		assertEquals(1, body.getDetails().size());
		assertEquals("Invalid parameters, please validate your information.", body.getMessage());
	}

	@Test
	void testHandleValidationExceptions404() throws JsonProcessingException {
		String response404 = "{\"codigo\": \"E404\",\"detalle\":\"ERROR 404\"}";
		requestExcepcion.handleValidationExceptions404(
				new ResponseStatusException(HttpStatus.NOT_FOUND, response404, new Exception(response404)));
		String response401 = "{\"codigo\": \"E401\",\"detalle\":\"ERROR 401\"}";
		requestExcepcion.handleValidationExceptions404(
				new ResponseStatusException(HttpStatus.UNAUTHORIZED, response401, new Exception(response401)));
		String response400 = "{\"codigo\": \"E400\",\"detalle\":\"ERROR 400\"}";
		requestExcepcion.handleValidationExceptions404(
				new ResponseStatusException(HttpStatus.BAD_REQUEST, response400, new Exception(response400)));
	}
}
