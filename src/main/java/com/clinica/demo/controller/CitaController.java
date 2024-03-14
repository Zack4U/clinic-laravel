package com.clinica.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clinica.demo.model.Cita;
import com.clinica.demo.repository.CitaRepository;

@Controller
@RequestMapping("/citas")

public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    // Metodos del controlador

    // Metodo para agregar una cita
    @PostMapping("/new")
    public Cita add(@RequestBody Cita cita) {
        return citaRepository.save(cita);
    }

    // Metodo para listar todas las citas
    @GetMapping("/list")
    public Iterable<Cita> list() {
        return citaRepository.findAll();
    }

}
