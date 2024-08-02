package com.gs.training.petardocore.exception;

import java.util.Collections;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.model.GenericResponse;

/**
 * <b>HandlerExceptions.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @descripcion: HandlerExceptions Class
 * @author: CoE Microservicios
 */
@ControllerAdvice
public class HandlerExceptions {

	/**
	 * Handle api request exception response entity.
	 *
	 * @param e e
	 * @return {@link ResponseEntity}
	 * @see ResponseEntity
	 * @see Object
	 */
	@ExceptionHandler(value = { GenericException.class })
	public ResponseEntity<GenericResponse> handleApiRequestException(GenericException e) {

		return ExceptionsManager.returnResponseEntity(e);
	}

	/**
	 * Handle validation exceptions response entity.
	 *
	 * @return the response entity
	 */
	@ExceptionHandler(RequestRejectedException.class)
	public ResponseEntity<GenericResponse> handleRequestRejectedException(RequestRejectedException ex) {

		return ExceptionsManager.returnResponseEntity(
				new GenericException(Collections.singletonList(ex.getMessage()), EnumHttpMessages.E400));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<GenericResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		String errorMessage = "Validation error: "
				+ Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();

		return ExceptionsManager.returnResponseEntity(
				new GenericException(Collections.singletonList(errorMessage), EnumHttpMessages.E400));
	}

	/**
	 * Handle validation exceptions response entity.
	 *
	 * @return the response entity
	 */
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<GenericResponse> handleNumberFormatException() {

		return ExceptionsManager.returnResponseEntity(new GenericException(
				Collections.singletonList("Parámetros no válidos, por favor valide su información."),
				EnumHttpMessages.E400));
	}

	/**
	 * Handle validation format response entity.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	public ResponseEntity<GenericResponse> handleValidationFormat(HttpMessageNotReadableException ex) {

		return ExceptionsManager.returnResponseEntity(new GenericException(
				Collections.singletonList("Parámetros no válidos, por favor valide su información."),
				EnumHttpMessages.E400));
	}
}
