package com.gs.training.petardocore.exception;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.training.petardocore.constant.PetardoCoreConstants;
import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.model.GenericResponse;

/**
 * The type Request excepcion.
 */
@RestControllerAdvice
public class RequestExceptionHandler {
	
	/**
	 * my regex
	 */
	private static final Pattern myRegex = Pattern.compile("/\\/");
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestExceptionHandler.class);

	/**
	 * Handle validation exceptions 401 response entity.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { MissingServletRequestParameterException.class })
	public ResponseEntity<GenericResponse> handleValidationRequestParameterException(
			MissingServletRequestParameterException ex) {
		
		List<String> details = new ArrayList<>();

		details.add("El valor " + ex.getParameterName() + " es requerido");

		return ExceptionsManager.returnResponseEntity(new GenericException(details, EnumHttpMessages.E400));
	}

	/**
	 * Handle validation exceptions 404 response entity.
	 *
	 * @param ex the ex
	 * @return the response entity
	 * @throws com.fasterxml.jackson.core.JsonProcessingException the json
	 *                                                            processing
	 *                                                            exception
	 */
	@ExceptionHandler(value = { ResponseStatusException.class })
	public ResponseEntity<GenericResponse> handleValidationExceptions404(ResponseStatusException ex)
			throws JsonProcessingException {
		
		List<String> details = new ArrayList<>();
		GenericException genericException = null;
		String str = ex.getMessage();
		str = str.substring(str.indexOf('{')).replaceAll(myRegex.pattern(), "");
		Map<String, String> map = new ObjectMapper().readValue(str, HashMap.class);
		details.add(map.get(PetardoCoreConstants.DETAIL));
		
		if (ex.getMessage().contains(PetardoCoreConstants.E404)) {
			
			genericException = new GenericException(details, EnumHttpMessages.E404);
		}
		
		if (ex.getMessage().contains(PetardoCoreConstants.E401)) {
			
			genericException = new GenericException(details, EnumHttpMessages.E401);
		}
		
		if (ex.getMessage().contains(PetardoCoreConstants.E400)) {
			
			genericException = new GenericException(details, EnumHttpMessages.E400);
		}
		
		return ExceptionsManager.returnResponseEntity(genericException);
	}

	/**
	 * Handle validation format response entity.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<GenericResponse> handleValidationFormat(HttpMessageNotReadableException ex) {
		
		return ExceptionsManager.returnResponseEntity(new GenericException(
				Collections.singletonList("Solicitud mal formada, favor de validar."), EnumHttpMessages.E400));
	}
	
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<GenericResponse> handleDataAccessException(DataAccessException ex) {
		LOGGER.error("DataAccessException occurred", ex);
		String folio = generateUniqueFolio();
		return createErrorResponse("400.Elektra-Criptomonedas-Gestion-Cotizaciones.400001",
								   "El servidor no pudo interpretar la solicitud dada una sintaxis inválida.",
								   folio,
								   "https://baz-developer.bancoazteca.com.mx/info#400.Elektra-Criptomonedas-Gestion-Cotizaciones.400001",
								   List.of("Parámetros incorrectos."));
	}
	
	private ResponseEntity<GenericResponse> createErrorResponse(String codigo, String mensaje, String folio, String info, List<String> detalles) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("codigo", codigo);
		errorDetails.put("mensaje", mensaje);
		errorDetails.put("folio", folio);
		errorDetails.put("info", info);
		errorDetails.put("detalles", detalles);

		GenericResponse genericResponse = new GenericResponse();
		return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
	}
	
	private String generateUniqueFolio() {
		return UUID.randomUUID().toString();
	}
}
