package com.clinica.demo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; // Import the Entity class
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacientes")

public class Paciente {

    @Id
    @Column(name = "cedula", nullable = false, length = 10)
    private int cedula;
    @Column(name = "nombres", nullable = false, length = 30)
    private String nombres;
    @Column(name = "apellidos", nullable = false, length = 30)
    private String apellidos;
    @Column(name = "telefono", nullable = false, length = 30)
    private String telefono;
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;
    @Column(name = "direccion", nullable = false, length = 80)
    private String direccion;
    @Column(name = "fechaNacimiento", nullable = false, length = 30)
    private Date fechaNacimiento;

    // constructors

    public Paciente() {
    }

    public Paciente(int cedula, String nombres, String apellidos, String telefono, String email, String direccion,
            Date fechaNacimiento) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    // getters and setters

    public int getCedula() {
        return this.cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
