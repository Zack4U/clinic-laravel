package com.clinica.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clinica.demo.exceptions.ResourceNotFoundException;
import com.clinica.demo.model.Paciente;
import com.clinica.demo.repository.PacienteRepository;

@Controller
@RequestMapping("/pacientes")

public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    // metodos crud para el controlador

    // metodo para agregar un paciente
    @PostMapping("/new")
    public String add(@ModelAttribute Paciente paciente, Model model) {
        pacienteRepository.save(paciente);
        model.addAttribute("mensaje", "Paciente creado correctamente");
        return "paciente-new_res";
    }

    // Metodo para listar todos los pacientes
    @GetMapping("/list")
    public Iterable<Paciente> list() {
        return pacienteRepository.findAll();
    }

    // actualizar un paciente por id
    @PostMapping("/edit/{cedula}")
    public String update(@PathVariable int cedula, @ModelAttribute Paciente paciente, Model model) {
        Paciente pacienteExistente = pacienteRepository.findById(cedula)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el paciente con la cedula: " + cedula));
        pacienteExistente.setEmail(paciente.getEmail());
        pacienteExistente.setDireccion(paciente.getDireccion());
        pacienteExistente.setTelefono(paciente.getTelefono());

        // Actualizar otros campos según sea necesario
        pacienteRepository.save(pacienteExistente);
        model.addAttribute("mensaje", "Paciente editado correctamente");
        return "paciente-edit_res";
    }

    // eliminar un paciente por id
    @PostMapping("/delete/{cedula}")
    public String delete(@PathVariable int cedula) {
        Paciente paciente = pacienteRepository.findById(cedula)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el paciente con la cedula: " + cedula));
        pacienteRepository.deleteById(cedula);
        System.out.println(paciente);
        return "redirect:/pacientes/list-front";
    }

    // buscar paciente por id
    @GetMapping("/find/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable int id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el paciente con el id: " + id));
        return ResponseEntity.ok(paciente);
    }

    // Mostrar formulario de nuevo paciente
    @GetMapping("/new")
    public String showAdd() {
        return "paciente-new";
    }

    // Mostrar lista de pacientes
    @GetMapping("/list-front")
    public String listFront(Model modelo) {
        ArrayList<Paciente> lista = (ArrayList<Paciente>) pacienteRepository.findAll();
        modelo.addAttribute("pacientes", lista);
        return "paciente-list";
    }

    // Mostrar panel de confirmación para eliminar un paciente
    @GetMapping("/delete-front/{cedula}")
    public String showDelete(@PathVariable int cedula, Model model) {
        model.addAttribute("cedula", cedula);
        return "paciente-delete_conf";
    }

    // Mostrar formulario para editar un paciente
    @GetMapping("/edit/{cedula}")
    public String showEdit(@PathVariable int cedula, Model model) {
        Optional<Paciente> paciente = pacienteRepository.findById(cedula);
        if (paciente.isPresent()) {
            model.addAttribute("paciente", paciente.get());
            return "paciente-edit";
        } else {
            return "redirect:/pacientes/list";
        }
    }
}
