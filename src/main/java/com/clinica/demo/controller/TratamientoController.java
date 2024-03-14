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
import com.clinica.demo.model.Tratamiento;
import com.clinica.demo.repository.TratamientoRepository;

@Controller
@RequestMapping("/tratamiento")
public class TratamientoController {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    // Metodos del controlador

    // metodo para agregar un tipo de tratamiento
    @PostMapping("/new")
    public Tratamiento add(@RequestBody Tratamiento Tratamiento) {
        return tratamientoRepository.save(Tratamiento);
    }

    // Metodo para listar todos los tipos de tratamiento
    @GetMapping("/list")
    public Iterable<Tratamiento> list() {
        return tratamientoRepository.findAll();
    }

    // actualizar un tipo de tratamiento por id

    @PutMapping("/edit/{id}")
    public Tratamiento update(@PathVariable int id, @RequestBody Tratamiento tratamiento) {
        Tratamiento tratamientoExistente = tratamientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el tipo de tratamiento con el id: " + id));
        tratamientoExistente.setNombre(tratamiento.getNombre());
        tratamientoExistente.setDescripcion(tratamiento.getDescripcion());
        tratamientoExistente.setCosto(tratamiento.getCosto());
        // Actualizar otros campos según sea necesario
        return tratamientoRepository.save(tratamientoExistente);
    }

    // eliminar un tipo de tratamiento por id

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        tratamientoRepository.deleteById(id);
    }

}
