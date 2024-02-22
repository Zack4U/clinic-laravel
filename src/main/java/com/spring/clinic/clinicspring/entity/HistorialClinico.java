package com.spring.clinic.clinicspring.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class HistorialClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "historial_clinico_id")
    private List<Tratamiento> tratamientosRealizados;
    private String alergias;
    private String condicionesMedicas;
    private String medicamentos;

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Tratamiento> getTratamientosRealizados() {
        return tratamientosRealizados;
    }

    public void setTratamientosRealizados(List<Tratamiento> tratamientosRealizados) {
        this.tratamientosRealizados = tratamientosRealizados;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCondicionesMedicas() {
        return condicionesMedicas;
    }

    public void setCondicionesMedicas(String condicionesMedicas) {
        this.condicionesMedicas = condicionesMedicas;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }
}