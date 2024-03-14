package com.clinica.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clinica.demo.exceptions.ResourceNotFoundException;
import com.clinica.demo.model.tipoTratamiento;
import com.clinica.demo.repository.TipoTratamientoRepository;

@Controller
@RequestMapping("/tipoTratamiento")

public class tipoTratamientoController {

    @Autowired
    private TipoTratamientoRepository tipoTratamientoRespRepository;

    // Metodos del controlador

    // metodo para agregar un tipo de tratamiento
    @PostMapping("/new")
    public tipoTratamiento add(@RequestBody tipoTratamiento tipoTratamiento) {
        return tipoTratamientoRespRepository.save(tipoTratamiento);
    }

    // Metodo para listar todos los tipos de tratamiento
    @GetMapping("/list")
    public Iterable<tipoTratamiento> list() {
        return tipoTratamientoRespRepository.findAll();
    }

    // actualizar un tipo de tratamiento por id

    @PutMapping("/edit/{id}")
    public tipoTratamiento update(@PathVariable int id,
            @RequestBody tipoTratamiento tipoTratamiento) {
        tipoTratamiento tipoTratamientoExistente = tipoTratamientoRespRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el tipo de tratamiento con el id: " + id));
        tipoTratamientoExistente.setNombre(tipoTratamiento.getNombre());
        tipoTratamientoExistente.setDescripcion(tipoTratamiento.getDescripcion());
        tipoTratamientoExistente.setCosto(tipoTratamiento.getCosto());
        // Actualizar otros campos según sea necesario
        return tipoTratamientoRespRepository.save(tipoTratamientoExistente);
    }

    // eliminar un tipo de tratamiento por id
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        tipoTratamiento tipoTratamientoExistente = tipoTratamientoRespRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el tipo de tratamiento con el id: " + id));
        tipoTratamientoRespRepository.delete(tipoTratamientoExistente);
    }

}
