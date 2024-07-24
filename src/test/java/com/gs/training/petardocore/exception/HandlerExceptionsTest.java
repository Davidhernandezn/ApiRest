package com.gs.training.petardocore.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.model.GenericResponse;


class HandlerExceptionsTest
{
	private HandlerExceptions handlerExcepciones;

	@BeforeEach
	void setup()
	{
		handlerExcepciones = new HandlerExceptions();
	}

	@Test
	void testHandleRequestRejectedException()
	{
		ResponseEntity currentHandleRequestRejectedExceptionResult = handlerExcepciones
				.handleRequestRejectedException(new RequestRejectedException("An error occurred"));
		
		assertTrue(currentHandleRequestRejectedExceptionResult.hasBody());
		assertTrue(currentHandleRequestRejectedExceptionResult.getHeaders().isEmpty());
		assertEquals(HttpStatus.BAD_REQUEST, currentHandleRequestRejectedExceptionResult.getStatusCode());
		assertEquals(((GenericResponse) currentHandleRequestRejectedExceptionResult.getBody()).getInfoUrl(),
				((GenericResponse) currentHandleRequestRejectedExceptionResult.getBody()).getInfoUrl());
		assertEquals(1, ((GenericResponse) currentHandleRequestRejectedExceptionResult.getBody()).getDetails().size());
		assertEquals(((GenericResponse) currentHandleRequestRejectedExceptionResult.getBody()).getCode(),
				((GenericResponse) currentHandleRequestRejectedExceptionResult.getBody()).getCode());
		assertEquals("Invalid parameters, please validate your information.",
				((GenericResponse) currentHandleRequestRejectedExceptionResult.getBody()).getMessage());
	}

	@Test
	void testHandleNumberFormatException()
	{
		ResponseEntity currentHandleNumberFormatExceptionResult = handlerExcepciones.handleNumberFormatException();
		
		assertTrue(currentHandleNumberFormatExceptionResult.hasBody());
		assertTrue(currentHandleNumberFormatExceptionResult.getHeaders().isEmpty());
		assertEquals(HttpStatus.BAD_REQUEST, currentHandleNumberFormatExceptionResult.getStatusCode());
		assertEquals(((GenericResponse) currentHandleNumberFormatExceptionResult.getBody()).getInfoUrl(),
				((GenericResponse) currentHandleNumberFormatExceptionResult.getBody()).getInfoUrl());
		assertEquals(1, ((GenericResponse) currentHandleNumberFormatExceptionResult.getBody()).getDetails().size());
		assertEquals(((GenericResponse) currentHandleNumberFormatExceptionResult.getBody()).getCode(),
				((GenericResponse) currentHandleNumberFormatExceptionResult.getBody()).getCode());
		assertEquals("Invalid parameters, please validate your information.",
				((GenericResponse) currentHandleNumberFormatExceptionResult.getBody()).getMessage());
	}

	@Test
	void testHandleMethodArgumentNotValidException()
	{
		BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "objectName");
		bindingResult.addError(new FieldError("objectName", "field1", "message"));
		bindingResult.addError(new FieldError("objectName", "field2", "message"));
		MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

		ResponseEntity handleMethodArgumentNotValidExceptionTest = handlerExcepciones
				.handleMethodArgumentNotValidException(exception);
		Assertions.assertTrue(exception instanceof MethodArgumentNotValidException);
	}

	@Test
	void testHandleApiRequestException()
	{
		ResponseEntity currentHandleApiRequestExceptionResult = handlerExcepciones
				.handleApiRequestException(new GenericException(List.of("Error in the test"), EnumHttpMessages.E401));
		
		assertTrue(currentHandleApiRequestExceptionResult.hasBody());
		assertTrue(currentHandleApiRequestExceptionResult.getHeaders().isEmpty());
		assertEquals(HttpStatus.UNAUTHORIZED, currentHandleApiRequestExceptionResult.getStatusCode());
		assertEquals(1, ((GenericResponse) currentHandleApiRequestExceptionResult.getBody()).getDetails().size());
		assertEquals("Access to unauthorized resource.",
				((GenericResponse) currentHandleApiRequestExceptionResult.getBody()).getMessage());
	}

	@Test
	void testHandleValidationFormat()
	{
		ResponseEntity<GenericResponse> currentHandleValidationFormatResult = handlerExcepciones
				.handleValidationFormat(new HttpMessageNotReadableException("https://example.org/example"));
		assertTrue(currentHandleValidationFormatResult.hasBody());
		assertTrue(currentHandleValidationFormatResult.getHeaders().isEmpty());
		assertEquals(HttpStatus.BAD_REQUEST, currentHandleValidationFormatResult.getStatusCode());
		GenericResponse body = currentHandleValidationFormatResult.getBody();
		assertEquals(1, body.getDetails().size());
		assertEquals("Invalid parameters, please validate your information.", body.getMessage());
	}
}
