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
public class GenericResponse <T>{
	
	/**
	 * Request HTTP Status Code.
	 */
	private String mensaje;
	/**
	 * Information about the request status http.
	 */
	private String codigo;
	/**
	 * Request Traceability Identifier.
	 */
	private String folio;
	/**
	 * Error Information URL.
	 */
	//private Object infoUrl;
	/**
	 * Details of the error if the request failed.
	 */
	private List<String> detalles;

	//private Object data;
    private T resultado;


	public GenericResponse() {
		this.mensaje = EnumHttpMessages.EOK_MESSAGE;
		this.folio = Folio.HOLDER.get();
	}

	/**public GenericResponse(Object data) {
		//this.message = EnumHttpMessages.EOK_MESSAGE;
		//this.transactionId = Folio.HOLDER.get();
		this();
		this.data = data;
	}	**/
	
	public GenericResponse(T resultado) {
	        this.setMensaje(EnumHttpMessages.EOK_MESSAGE);
	        this.setFolio(Folio.HOLDER.get());
	        this.setResultado(resultado);
	        System.out.println("##RESULTADO"+ resultado);
	}

	public GenericResponse(List<String> details, EnumHttpMessages enumHttpMessages) {
		this();
		this.codigo = enumHttpMessages.getStatus();
		this.mensaje= enumHttpMessages.getMessage();
		//this.transactionId = Folio.HOLDER.get();
		//this.infoUrl = enumHttpMessages.getInfo();
		this.detalles = new ArrayList<>(details);
	}
	
	
	//GETTERS AND SETTERS
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public List<String> getDetalles() {
		return detalles;
	}
	
	public void setDetalles(List<String> detalles) {
		this.detalles = detalles;
	}

	public T getResultado() {
		return resultado;
	}

	public void setResultado(T resultado) {
		this.resultado = resultado;
	}
	

	
}
