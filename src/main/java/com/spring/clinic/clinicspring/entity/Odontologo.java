package com.spring.clinic.clinicspring.entity;

import org.springframework.data.annotation.Id;

public class Odontologo {

    @Id
    private int id;
    private String nombres;
    private String apellidos;
    private String especialidad;
    private String telefono;
    private String email;

    // Constructor con parámetros
    public Odontologo(int id, String nombres, String apellidos,
            String especialidad, String telefono, String email) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    String getEmail() {
        return email;
    }

    // Setter methods
    void setId(int id) {
        this.id = id;
    }

    void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}