package com.gs.training.petardocore.dto;


public class PersonaDto {
	    private Long id;
	    private String nombre;
	    private String apellidoPaterno;
	    private String apellidoMaterno;
	    private int edad;
	    private String ciudad;
	    private String email;
	    private String telefono;
	    
	    public PersonaDto() {
	    }

	    public PersonaDto(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, int edad, String ciudad,
	                      String email, String telefono) {
	        this.id = id;
	        this.nombre = nombre;
	        this.apellidoPaterno = apellidoPaterno;
	        this.apellidoMaterno = apellidoMaterno;
	        this.edad = edad;
	        this.ciudad = ciudad;
	        this.email = email;
	        this.telefono = telefono;
	    }
	    
	    public Long getId() {
	        return id;
	    }
	    public void setId(Long id) {
	        this.id = id;
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
