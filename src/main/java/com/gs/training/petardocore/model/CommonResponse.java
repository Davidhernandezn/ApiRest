package com.gs.training.petardocore.model;

import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CommonResponse<T> {
    private String mensaje;
    private String codigo;
    private String folio;
    private T resultado;
    //private Map<String, Object> errorDetails;
    private String detalles;
    
    public CommonResponse() {
        this.setMensaje("Operación exitosa");
        this.setFolio(UUID.randomUUID().toString());
    }

    public CommonResponse(String mensaje, T resultado) {
        this.setMensaje(mensaje);
        this.setFolio(UUID.randomUUID().toString());
        this.setResultado(resultado);
    }

    public CommonResponse(String string, String string2, String string3, String string4, List<String> of) {
		// TODO Auto-generated constructor stub
	}

	public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public T getResultado() {
        return resultado;
    }

    public void setResultado(T resultado) {
        this.resultado = resultado;
    }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String string) {
		this.detalles = string;
	}

}