package com.gs.training.petardocore.exception;

import java.util.ArrayList;
import java.util.List;
import com.gs.training.petardocore.enums.EnumHttpMessages;
import lombok.Data;

/**
 * <b>GenericException.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @descripcion: GenericException Class
 * @author: CoE Microservicios
 */
@Data
public class GenericException extends RuntimeException {

	private final EnumHttpMessages enumHttpMessages;
	private final List<String> details;

	public GenericException(List<String> details, EnumHttpMessages enumHttpMessages) {

		super(details.get(0));
		this.details = new ArrayList<>(details);
		this.enumHttpMessages = enumHttpMessages;
	}

	public List<String> getDetails() {
		return details;
	}

	public EnumHttpMessages getEnumHttpMessages() {
		return enumHttpMessages;
	}
}
