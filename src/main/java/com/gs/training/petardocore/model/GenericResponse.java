package com.gs.training.petardocore.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.util.Folio;

/**
 * The type Generic insert response.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "codigo", "mensaje", "folio", "info", "detalles" })
public class GenericResponse<T> {

	/**
	 * Information about the request status http.
	 */
	private String mensaje;

	/**
	 * Request HTTP Status Code.
	 */
	private String codigo;

	/**
	 * Request Traceability Identifier.
	 */

	private String folio;

	/**
	 * Error Information URL.
	 */

	private Object info;

	/**
	 * Detalles del error si se presenta un error en la peticion.
	 */

	private List<String> detalles;

	private T resultado;

	public GenericResponse() {
		this.mensaje = EnumHttpMessages.EOK_MESSAGE;
		this.folio = Folio.HOLDER.get();
	}

	public GenericResponse(T resultado) {
		this.setCodigo(getCodigo());
		this.setMensaje(EnumHttpMessages.EOK_MESSAGE);
		this.setFolio(Folio.HOLDER.get());
		this.setResultado(resultado);
		this.setInfo(info);
	}

	public GenericResponse(List<String> details, EnumHttpMessages enumHttpMessages) {
		this.codigo = enumHttpMessages.getStatus();
		this.mensaje = enumHttpMessages.getMessage();
		this.detalles = new ArrayList<>(details);
		this.info = enumHttpMessages.getInfo();
	}

	public GenericResponse(EnumHttpMessages enumHttpMessages) {
		this.codigo = enumHttpMessages.getStatus();
		this.mensaje = enumHttpMessages.getMessage();
	}

	public GenericResponse(T resultado, EnumHttpMessages enumHttpMessages) {
		this.codigo = enumHttpMessages.getStatus();
		this.mensaje = enumHttpMessages.getMessage();
		this.folio = Folio.HOLDER.get();
		this.resultado = resultado;
	}

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

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}
}
