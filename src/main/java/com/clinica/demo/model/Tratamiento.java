package com.clinica.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; // Import the Entity class
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private double precio;
    @Column(nullable = true)
    private String procedimientos;
    @Column(nullable = true)
    private String resultados;
    @Column(nullable = true)
    private String medicamentos;
    @Column(nullable = true)
    private String instrucciones;

    @ManyToOne
    @JoinColumn(name = "tipoTratamiento", nullable = false)
    private TipoTratamiento tipoTratamiento;

    public Tratamiento() {
    }

    public Tratamiento(int id, String nombre, double precio, TipoTratamiento tipoTratamiento) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipoTratamiento = tipoTratamiento;
    }

    public Tratamiento(int id, String nombre, double precio, String procedimientos, String resultados,
            String medicamentos, String instrucciones, TipoTratamiento tipoTratamiento) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.procedimientos = procedimientos;
        this.resultados = resultados;
        this.medicamentos = medicamentos;
        this.instrucciones = instrucciones;
        this.tipoTratamiento = tipoTratamiento;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getProcedimientos() {
        return this.procedimientos;
    }

    public void setProcedimientos(String procedimientos) {
        this.procedimientos = procedimientos;
    }

    public String getResultados() {
        return this.resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public String getMedicamentos() {
        return this.medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getInstrucciones() {
        return this.instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public TipoTratamiento getTipoTratamiento() {
        return this.tipoTratamiento;
    }

    public void setTipoTratamiento(TipoTratamiento tipoTratamiento) {
        this.tipoTratamiento = tipoTratamiento;
    }

}
