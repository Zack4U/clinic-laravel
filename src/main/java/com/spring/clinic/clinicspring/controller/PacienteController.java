package com.spring.clinic.clinicspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.spring.clinic.clinicspring.entity.HistorialClinico;
import com.spring.clinic.clinicspring.entity.Odontologo;
import com.spring.clinic.clinicspring.entity.Paciente;
import com.spring.clinic.clinicspring.repository.PacienteRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping("/all")
    public Iterable<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Paciente getPaciente(@PathVariable int id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @PostMapping("/new")
    @ResponseBody
    public String newPaciente(@RequestParam String cedula,
            @RequestParam String nombres,
            @RequestParam String apellidos,
            @RequestParam Date fechaNacimiento,
            @RequestParam String telefono,
            @RequestParam String direccion,
            @RequestParam String email) {
        Paciente p = new Paciente();
        p.setCedula(cedula);
        p.setNombres(nombres);
        p.setApellidos(apellidos);
        p.setFechaNacimiento(fechaNacimiento);
        p.setTelefono(telefono);
        p.setDireccionResidencia(direccion);
        p.setEmail(email);

        pacienteRepository.save(p);
        return "Listo";
    }

    @PatchMapping("/paciente/{id}")
    public String updatePaciente(@PathVariable int id,
            @RequestBody Paciente pacienteDetails) {

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isPresent()) {
            Paciente p = optionalPaciente.get();

            if (pacienteDetails.getTelefono() != null) {
                p.setTelefono(pacienteDetails.getTelefono());
            }
            if (pacienteDetails.getEmail() != null) {
                p.setEmail(pacienteDetails.getEmail());
            }
            if (pacienteDetails.getDireccionResidencia() != null) {
                p.setDireccionResidencia(pacienteDetails.getDireccionResidencia());
            }

            pacienteRepository.save(p);
            return "Paciente actualizado exitosamente";
        } else {
            return "El paciente con el ID proporcionado no existe";
        }
    }

    @DeleteMapping("/{id}")
    public String deletePaciente(@PathVariable int id) {
        pacienteRepository.deleteById(id);
        return "Paciente eliminado exitosamente";
    }

    @GetMapping("/{id}/historial")
    public HistorialClinico getHistorialClinico(@PathVariable int id) {
        return pacienteRepository.findById(id).map(Paciente::getHistorialClinico).orElse(null);
    }
}