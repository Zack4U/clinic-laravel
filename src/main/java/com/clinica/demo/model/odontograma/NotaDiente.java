package com.clinica.demo.model.odontograma;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.clinica.demo.model.Diente;

import java.sql.Date;

@Entity
public class NotaDiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String contenido;
    private Date fecha;
    // diente al que pertenece la nota
    @ManyToOne
    @JoinColumn(name = "diente", nullable = false)
    private Diente diente;

    // Constructor, getters y setters
    public NotaDiente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Diente getDiente() {
        return diente;
    }

    public void setDiente(Diente diente) {
        this.diente = diente;
    }
}
