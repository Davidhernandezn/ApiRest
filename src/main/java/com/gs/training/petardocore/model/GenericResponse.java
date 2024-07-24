package com.gs.training.petardocore.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.util.Folio;

import lombok.Data;

/**
 * The type Generic insert response.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GenericResponse {
	
	/**
	 * Request HTTP Status Code.
	 */
	private String code;
	/**
	 * Information about the request status http.
	 */
	private String message;
	/**
	 * Request Traceability Identifier.
	 */
	private String transactionId;
	/**
	 * Error Information URL.
	 */
	private String infoUrl;
	/**
	 * Details of the error if the request failed.
	 */
	private List<String> details;

	private Object data;

	public GenericResponse() {
		
		this.message = EnumHttpMessages.EOK_MESSAGE;
		this.transactionId = Folio.HOLDER.get();
	}

	public GenericResponse(Object data) {
		
		this.message = EnumHttpMessages.EOK_MESSAGE;
		this.transactionId = Folio.HOLDER.get();
		this.data = data;
	}

	public GenericResponse(List<String> details, EnumHttpMessages enumHttpMessages) {
		
		this.code = enumHttpMessages.getStatus();
		this.message = enumHttpMessages.getMessage();
		this.transactionId = Folio.HOLDER.get();
		this.infoUrl = enumHttpMessages.getInfo();
		this.details = new ArrayList<>(details);
	}
}
