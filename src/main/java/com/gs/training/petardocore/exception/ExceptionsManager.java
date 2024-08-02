package com.gs.training.petardocore.exception;

import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import com.google.gson.Gson;
import com.gs.training.petardocore.constant.PetardoCoreConstants;
import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.model.GenericResponse;
import com.gs.ftt.log.LogMonitor;

/**
 * <b>ExceptionsManager.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @descripcion: ExceptionManager Class
 * @author: CoE Microservicios
 */
public final class ExceptionsManager {

	private static final int BAD_REQUEST = 400;
	private static final int UNAUTHORIZED = 401;
	private static final int NOT_FOUND = 404;
	private static final LogMonitor LOGGER = new LogMonitor(ExceptionsManager.class);

	private ExceptionsManager() {

		super();
	}

	public static GenericException returnGenericException(Exception ex) {

		GenericException feedback = null;

		if (ex instanceof GenericException) {

			feedback = (GenericException) ex;
		} else if (ex instanceof ResponseStatusException responseStatusException) {

			feedback = parseResponseStatusExceptionToGenericException(responseStatusException);
		} else {

			feedback = new GenericException(List.of("Internal Server Error"), EnumHttpMessages.E500);
		}

		return feedback;
	}

	public static ResponseEntity<GenericResponse> returnResponseEntity(GenericException genericException) {

		GenericResponse response = new GenericResponse(genericException.getDetails(),
				genericException.getEnumHttpMessages());

		return new ResponseEntity<GenericResponse>(response, genericException.getEnumHttpMessages().getHttpStatus());
	}

	public static GenericException parseResponseStatusExceptionToGenericException(
			ResponseStatusException responseStatusException) {

		GenericException genericException = null;

		try {

			List<String> details = new Gson().fromJson(responseStatusException.getReason(), GenericResponse.class)
					.getDetalles();

			HttpStatusCode code = responseStatusException.getStatusCode();
			int codeResponse = code.value();

			genericException = switch (codeResponse) {

			case BAD_REQUEST -> new GenericException(details, EnumHttpMessages.E400);
			case UNAUTHORIZED -> new GenericException(details, EnumHttpMessages.E401);
			case NOT_FOUND -> new GenericException(details, EnumHttpMessages.E404);
			default -> new GenericException(details, EnumHttpMessages.E500);
			};
		} catch (Exception ex) {

			LOGGER.error(PetardoCoreConstants.LOG_ERROR, ex, ex.getStackTrace());
			genericException = new GenericException(List.of("Internal Server Error"), EnumHttpMessages.E500);
		}

		return genericException;
	}
}
