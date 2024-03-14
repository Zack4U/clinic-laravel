package com.clinica.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; // Import the Entity class
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "citas")

public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fecha", nullable = false, length = 30)
    private String fecha;
    @Column(name = "hora", nullable = false, length = 30)
    private String hora;
    @Column(name = "motivo", nullable = false, length = 30)
    private String motivo;
    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

    // tratamiento relacionado con la entidad tratamiento
    @ManyToOne
    @JoinColumn(name = "tratamiento_id", nullable = false) // Nombre de la columna en la tabla de citas que almacena el
                                                           // ID del tratamiento
    private Tratamiento tratamiento;
    // paciente relacionado con la entidad paciente
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = true) // Nombre de la columna en la tabla de citas que almacena el ID
                                                       // del paciente
    private Paciente paciente;
    // odontologo relacionado con la entidad odontologo
    @ManyToOne
    @JoinColumn(name = "odontologo_id", nullable = true) // Nombre de la columna en la tabla de citas que almacena el ID
                                                         // del odontólogo
    private Odontologo odontologo;

    // constructors

    public Cita() {
    }

    public Cita(int id, String fecha, String hora, String motivo, Tratamiento tratamiento, String estado,
            Paciente paciente, Odontologo odontologo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.tratamiento = tratamiento;
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

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
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

    public Tratamiento getTratamiento() {
        return this.tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getEstado() {
        return this.estado;
    }

}
