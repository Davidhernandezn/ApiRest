package com.gs.training.petardocore.enums;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import com.gs.training.petardocore.constant.PetardoCoreConstants;
import lombok.Getter;
import java.util.Locale;

/**
 * <b>EnumHttpMessages.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @descripcion: EnumHttpMessages Class
 * @author: CoE Microservicios
 */
@Getter
public enum EnumHttpMessages {
	/**
	 * The M 204.
	 */
	M204(HttpStatus.NO_CONTENT.value() + "." + getBasePathCapitalized(PetardoCoreConstants.BASE_PATH) + ".00",
			EnumHttpMessages.M204_MESSAGE, HttpStatus.NO_CONTENT),
	/**
	 * The E 400.
	 */
	E400(HttpStatus.BAD_REQUEST.value() + "." + getBasePathCapitalized(PetardoCoreConstants.BASE_PATH) + ".00",
			EnumHttpMessages.E400_MESSAGE, HttpStatus.BAD_REQUEST),
	/**
	 * The E 401.
	 */
	E401(HttpStatus.UNAUTHORIZED.value() + "." + getBasePathCapitalized(PetardoCoreConstants.BASE_PATH) + ".00",
			EnumHttpMessages.E401_MESSAGE, HttpStatus.UNAUTHORIZED),
	/**
	 * The E 404.
	 */
	E404(HttpStatus.NOT_FOUND.value() + "." + getBasePathCapitalized(PetardoCoreConstants.BASE_PATH) + ".00",
			EnumHttpMessages.E404_MESSAGE, HttpStatus.NOT_FOUND),
	/**
	 * The E 500.
	 */
	E500(HttpStatus.INTERNAL_SERVER_ERROR.value() + "." + getBasePathCapitalized(PetardoCoreConstants.BASE_PATH)
			+ ".00", EnumHttpMessages.E500_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);

	public static final String M204_MESSAGE = "Sin contenido";
	public static final String EOK_MESSAGE  = "Operación exitosa.";
	public static final String E400_MESSAGE = "Solicitud mal formada, favor de validar.";
	public static final String E401_MESSAGE = "No estas autorizado, favor de validar.";
	public static final String E404_MESSAGE = "Información no encontrada, favor validar.";
	public static final String E500_MESSAGE = "Problema interno en el servidor, favor de validar.";
	private final String status;
	private final String message;
	private final String info;
	private final HttpStatus httpStatus;

	EnumHttpMessages(String status, String message, HttpStatus httpStatus) {
		this.status = status;
		this.message = message;
		this.info = PetardoCoreConstants.URL_DEVELOPER_INFORMATION_CODES + "#" + status;
		this.httpStatus = httpStatus;
	}

	private static String getBasePathCapitalized(String basePath) {

		String[] words = basePath.split("/");

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < words.length - 1; i++) {

			String word = words[i];

			if (word.isEmpty()) {

				continue;
			}

			String[] parts = word.split("-");

			for (int j = 0; j < parts.length; j++) {

				result.append(parts[j].substring(0, 1).toUpperCase(Locale.US));
				result.append(parts[j].substring(1).toLowerCase(Locale.US));

				if (j < parts.length - 1) {

					result.append("-");
				}
			}

			result.append("-");
		}

		result.setLength(result.length() - 1);

		return result.toString();
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Object getInfo() {
		return info;
	}

	public HttpStatusCode getHttpStatus() {
		return httpStatus;
	}
}
