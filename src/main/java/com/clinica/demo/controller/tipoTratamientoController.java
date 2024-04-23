package com.clinica.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clinica.demo.exceptions.ResourceNotFoundException;
import com.clinica.demo.model.TipoTratamiento;
import com.clinica.demo.repository.TipoTratamientoRepository;

@Controller
@RequestMapping("/tipos_tratamientos")

public class tipoTratamientoController {

    @Autowired
    private TipoTratamientoRepository tipoTratamientoRepository;

    // Metodos del controlador

    // metodo para agregar un tipo de tratamiento
    @PostMapping("/new")
    public String add(@ModelAttribute TipoTratamiento tipoTratamiento, Model model) {
        tipoTratamientoRepository.save(tipoTratamiento);
        model.addAttribute("mensaje", "Tipo de tratamiento creado correctamente");
        return "tipo_tratamiento-new_res";
    }

    // Metodo para listar todos los tipos de tratamiento
    @GetMapping("/list")
    public Iterable<TipoTratamiento> list() {
        return tipoTratamientoRepository.findAll();
    }

    // actualizar un tipo de tratamiento por id

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id,
            @ModelAttribute TipoTratamiento tipoTratamiento, Model model) {
        TipoTratamiento tipoTratamientoExistente = tipoTratamientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el tipo de tratamiento con el id: " + id));
        tipoTratamientoExistente.setNombre(tipoTratamiento.getNombre());
        tipoTratamientoExistente.setDescripcion(tipoTratamiento.getDescripcion());
        // Actualizar otros campos según sea necesario
        tipoTratamientoRepository.save(tipoTratamientoExistente);
        model.addAttribute("mensaje", "Tipo de Tratamiento editado correctamente");
        return "tipo_tratamiento-edit_res";
    }

    // eliminar un tipo de tratamiento por id
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        try {
            tipoTratamientoRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "redirect:/tipo_tratamiento/list-front";
    }

    @GetMapping("/new")
    public String showAdd() {
        return "tipo_tratamiento-new";
    }

    // Mostrar lista de pacientes
    @GetMapping("/list-front")
    public String listFront(Model modelo) {
        ArrayList<TipoTratamiento> lista = (ArrayList<TipoTratamiento>) tipoTratamientoRepository.findAll();
        modelo.addAttribute("tipos_tratamientos", lista);
        return "tipo_tratamiento-list";
    }

    // Mostrar panel de confirmación para eliminar un paciente
    @GetMapping("/delete-front/{id}")
    public String showDelete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "tipo_tratamiento-delete_conf";
    }

    // Mostrar formulario para editar un paciente
    @GetMapping("/edit/{cedula}")
    public String showEdit(@PathVariable int cedula, Model model) {
        Optional<TipoTratamiento> tipoTratamiento = tipoTratamientoRepository.findById(cedula);
        if (tipoTratamiento.isPresent()) {
            model.addAttribute("tipo_tratamiento", tipoTratamiento.get());
            return "tipo_tratamiento-edit";
        } else {
            return "redirect:/tipo_tratamiento/list-front";
        }
    }

}
