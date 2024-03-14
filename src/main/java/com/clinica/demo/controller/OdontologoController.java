package com.clinica.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clinica.demo.exceptions.ResourceNotFoundException;
import com.clinica.demo.model.Odontologo;
import com.clinica.demo.repository.OdontologoRepository;

@Controller
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoRepository odontologoRepository;

    // Metodos del controlador

    // Metodo para agregar un odontologo
    @PostMapping("/new")
    public String add(@ModelAttribute Odontologo odontologo, Model model) {
        odontologoRepository.save(odontologo);
        model.addAttribute("mensaje", "Odontólogo creado correctamente");
        return "odontologo-new_res";
    }

    // Metodo para listar todos los odontologos
    @GetMapping("/list")
    public Iterable<Odontologo> list(Model modelo) {
        return odontologoRepository.findAll();
    }

    // Actualizar un odontologo por id
    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute Odontologo odontologo, Model model) {
        Odontologo odontologoExistente = odontologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el odontologo con el id: " + id));
        odontologoExistente.setNombres(odontologo.getNombres());
        odontologoExistente.setApellidos(odontologo.getApellidos());
        odontologoExistente.setEmail(odontologo.getEmail());
        odontologoExistente.setEspecialidad(odontologo.getEspecialidad());
        odontologoExistente.setTelefono(odontologo.getTelefono());

        // Actualizar otros campos según sea necesario
        odontologoRepository.save(odontologoExistente);
        model.addAttribute("mensaje", "Odontólogo editado correctamente");
        return "odontologo-edit_res";
    }

    // Eliminar un odontologo por id
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Odontologo odontologo = odontologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el odontologo con el id: " + id));
        odontologoRepository.deleteById(id);
        return "redirect:/odontologos/list-front";
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Odontologo> findById(@PathVariable int id) {
        try {
            Odontologo odontologo = odontologoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No existe el odontologo con el id: " + id));
            return ResponseEntity.ok(odontologo);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mostrar formulario de nuevo odontologo
    @GetMapping("/new")
    public String showAdd() {
        return "odontologo-new";
    }

    // Mostrar lista de odontologos
    @GetMapping(path = "/list-front")
    public String listFront(Model modelo) {
        ArrayList<Odontologo> lista = (ArrayList<Odontologo>) odontologoRepository.findAll();
        modelo.addAttribute("odontologos", lista);
        return "odontologo-list";
    }

    // Mostrar panel de confirmación para eliminar un odontologo
    @GetMapping("/delete-front/{id}")
    public String showDelete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "odontologo-delete_conf";
    }

    // Mostrar formulario para editar un odontologo
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id, Model model) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (odontologo.isPresent()) {
            model.addAttribute("odontologo", odontologo.get());
            return "odontologo-edit";
        } else {
            return "redirect:/odontologos/list";
        }
    }
}