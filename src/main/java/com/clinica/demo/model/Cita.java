package com.clinica.demo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; // Import the Entity class
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private Date fecha;
    @Column(nullable = false)
    private String hora;
    @Column(nullable = false)
    private String motivo;
    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "paciente", nullable = false)
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "tipoTratamiento", nullable = false)
    private TipoTratamiento tipoTratamiento;
    @ManyToOne
    @JoinColumn(name = "odontologo", nullable = false)
    private Odontologo odontologo;

    public Cita() {
    }

    public Cita(int id, Date fecha, String hora, String motivo, String estado, TipoTratamiento tipoTratamiento,
            Paciente paciente, Odontologo odontologo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.tipoTratamiento = tipoTratamiento;
        this.estado = estado;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    // getters and setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return this.hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public TipoTratamiento getTipoTratamiento() {
        return this.tipoTratamiento;
    }

    public void setTipoTratamiento(TipoTratamiento tipoTratamiento) {
        this.tipoTratamiento = tipoTratamiento;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return this.odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

}
