package com.clinica.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.clinica.demo.model.TipoTratamiento;
import com.clinica.demo.model.Tratamiento;
import com.clinica.demo.repository.TipoTratamientoRepository;
import com.clinica.demo.repository.TratamientoRepository;

@Controller
@RequestMapping("/tratamientos")
public class TratamientoController {

    @Autowired
    private TratamientoRepository tratamientoRepository;
    @Autowired
    private TipoTratamientoRepository tipoTratamientoRepository;

    // Metodos del controlador

    // metodo para agregar un tipo de tratamiento
    @PostMapping("/new")
    public String add(@ModelAttribute Tratamiento tratamiento, Model model) {
        TipoTratamiento tipoTratamiento = tipoTratamientoRepository.findById(tratamiento.getTipoTratamiento().getId())
                .orElse(null);
        tratamiento.setTipoTratamiento(tipoTratamiento);
        tratamientoRepository.save(tratamiento);
        model.addAttribute("mensaje", "Tipo de tratamiento creado correctamente");
        return "tratamiento-new_res";
    }

    // Metodo para listar todos los tipos de tratamiento
    @GetMapping("/list")
    public Iterable<Tratamiento> list() {
        return tratamientoRepository.findAll();
    }

    // actualizar un tipo de tratamiento por id

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute Tratamiento tratamiento, Model model) {
        Tratamiento tratamientoExistente = tratamientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el tipo de tratamiento con el id: " + id));
        tratamientoExistente.setInstrucciones(tratamiento.getInstrucciones());
        tratamientoExistente.setPrecio(tratamiento.getPrecio());
        tratamientoExistente.setNombre(tratamiento.getNombre());
        tratamientoExistente.setResultados(tratamiento.getResultados());
        tratamientoExistente.setMedicamentos(tratamiento.getMedicamentos());
        tratamientoExistente.setProcedimientos(tratamiento.getProcedimientos());
        tratamientoExistente.setTipoTratamiento(tratamiento.getTipoTratamiento());
        // Actualizar otros campos según sea necesario
        tratamientoRepository.save(tratamientoExistente);
        model.addAttribute("mensaje", "Tipo de Tratamiento editado correctamente");
        return "tratamiento-edit_res";
    }

    // eliminar un tipo de tratamiento por id

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        tratamientoRepository.deleteById(id);
    }

    @GetMapping("/new")
    public String showAdd(Model model) {
        ArrayList<TipoTratamiento> tipos = (ArrayList<TipoTratamiento>) tipoTratamientoRepository.findAll();
        model.addAttribute("tipos", tipos);
        System.err.println(tipos);
        return "tratamiento-new";
    }

    // Mostrar lista de pacientes
    @GetMapping("/list-front")
    public String listFront(Model model) {
        ArrayList<Tratamiento> lista = (ArrayList<Tratamiento>) tratamientoRepository.findAll();
        model.addAttribute("tratamientos", lista);
        ArrayList<TipoTratamiento> tipos = (ArrayList<TipoTratamiento>) tipoTratamientoRepository.findAll();
        model.addAttribute("tipos", tipos);
        return "tratamiento-list";
    }

    // Mostrar panel de confirmación para eliminar un paciente
    @GetMapping("/delete-front/{id}")
    public String showDelete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "tratamiento-delete_conf";
    }

    // Mostrar formulario para editar un paciente
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id, Model model) {
        Optional<Tratamiento> tratamiento = tratamientoRepository.findById(id);
        if (tratamiento.isPresent()) {
            model.addAttribute("tratamiento", tratamiento.get());
            ArrayList<TipoTratamiento> tipos = (ArrayList<TipoTratamiento>) tipoTratamientoRepository.findAll();
            model.addAttribute("tipos", tipos);
            return "tratamiento-edit";
        } else {
            return "redirect:/tratamientos/list-front";
        }
    }

}
