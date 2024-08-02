package com.gs.training.petardocore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Respuesta de Registro de Persona")
public class PersonaDto {
	private Long idPersona;

	@NotNull(message = "El nombre es requerido")
	private String nombre;

	@NotNull(message = "El apellido paterno es requerido")
	private String apellidoPaterno;

	@NotNull(message = "El apellido materno es requerido")
	private String apellidoMaterno;

	@NotNull(message = "La edad es requerida")
	// @Pattern(regexp = "\\d{2}", message = "Ingrese una edad validad")
	private int edad;

	@NotNull(message = "La ciudad es requerida")
	private String ciudad;

	@NotNull(message = "El email es requerido")
	@Email(message = "El correo electrónico debe ser válido")
	private String email;

	@NotNull(message = "El telefono es requerido")
	@Pattern(regexp = "\\d{10}", message = "El teléfono debe tener 10 dígitos")
	private String telefono;

	public PersonaDto() {
	}

	public PersonaDto(Long idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, int edad,
			String ciudad, String email, String telefono) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.edad = edad;
		this.ciudad = ciudad;
		this.email = email;
		this.telefono = telefono;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long id) {
		this.idPersona = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
