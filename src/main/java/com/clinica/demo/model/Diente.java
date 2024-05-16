package com.clinica.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.clinica.demo.model.odontograma.EstadoDiente;
import com.clinica.demo.model.odontograma.NotaDiente;
import com.clinica.demo.model.odontograma.TipoDiente;

import java.util.List;

@Entity
public class Diente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private TipoDiente tipo;

    private EstadoDiente estado;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotaDiente> nota;

    public Diente() {
    }

    public Diente(TipoDiente tipo, EstadoDiente estado,
            List<NotaDiente> nota) {
        this.tipo = tipo;
        this.estado = estado;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoDiente getTipo() {
        return tipo;
    }

    public void setTipo(TipoDiente tipo) {
        this.tipo = tipo;
    }

    public EstadoDiente getEstado() {
        return estado;
    }

    public void setEstado(EstadoDiente estado) {
        this.estado = estado;
    }

    public List<NotaDiente> getNota() {
        return nota;
    }

    public void setNota(List<NotaDiente> nota) {
        this.nota = nota;
    }

    public void addNota(NotaDiente nota) {
        this.nota.add(nota);
    }

}