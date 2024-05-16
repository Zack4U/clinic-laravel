package com.clinica.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.clinica.demo.model.odontograma.EstadoDiente;
import com.clinica.demo.model.odontograma.NotaDiente;
import com.clinica.demo.model.odontograma.TipoDiente;

@Entity
public class Odontograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diente> dientes;

    @ManyToOne
    @JoinColumn(name = "paciente")
    private Paciente paciente;

    public Odontograma() {
    }

    public Odontograma(List<Diente> dientes, Paciente paciente) {
        this.dientes = dientes;
        this.paciente = paciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Diente> getDientes() {
        return dientes;
    }

    public void setDientes(List<Diente> dientes) {
        this.dientes = dientes;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void crearDientes(String edadGrupo) {
        List<Integer> tiposExcluidosMenores = Arrays.asList(6, 7, 8, 14, 15, 16, 22, 23, 24, 30, 31, 32);
        List<Diente> dientes = new ArrayList<>();
        for (int i = 1; i <= 32; i++) {
            if ("menor".equals(edadGrupo) && tiposExcluidosMenores.contains(i)) {
                continue;
            }
            Diente diente = new Diente();
            diente.setEstado(EstadoDiente.SANO);
            diente.setTipo(TipoDiente.valueOf("TIPO_" + i));
            diente.setNota(new ArrayList<NotaDiente>());
            dientes.add(diente);
        }
        this.dientes = dientes;
    }

}