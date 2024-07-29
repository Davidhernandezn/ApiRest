package com.gs.training.petardocore.model;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CommonResponse<T> {
private String mensaje;
private String folio;
private T data;

    public CommonResponse() {
        this.setMensaje("Operación exitosa"); // Mensaje predeterminado
        this.setFolio(UUID.randomUUID().toString());
    }

    public CommonResponse(String mensaje, T data) {
        this.setMensaje(mensaje);
        this.setFolio(UUID.randomUUID().toString());
        this.setData(data);
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}


