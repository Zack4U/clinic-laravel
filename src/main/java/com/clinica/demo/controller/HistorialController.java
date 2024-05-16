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
import com.clinica.demo.model.Historial;
import com.clinica.demo.model.Odontologo;
import com.clinica.demo.model.Paciente;
import com.clinica.demo.model.Tratamiento;
import com.clinica.demo.repository.HistorialRepository;
import com.clinica.demo.repository.OdontologoRepository;
import com.clinica.demo.repository.PacienteRepository;
import com.clinica.demo.repository.TratamientoRepository;

@Controller
@RequestMapping("/historiales")
public class HistorialController {
    @Autowired
    private HistorialRepository historialRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private TratamientoRepository tratamientoRepository;

    // Metodos del controlador

    // Metodo para agregar una cita
    @PostMapping("/new")
    public String add(@ModelAttribute Historial historial, Model model) {
        Tratamiento tratamiento = tratamientoRepository.findById(historial.getTratamiento().getId())
                .orElse(null);
        historial.setTratamiento(tratamiento);
        Odontologo odontologo = odontologoRepository.findById(historial.getOdontologo().getId())
                .orElse(null);
        historial.setOdontologo(odontologo);
        Paciente paciente = pacienteRepository.findById(historial.getPaciente().getCedula())
                .orElse(null);
        historial.setPaciente(paciente);
        historialRepository.save(historial);
        model.addAttribute("mensaje", "Historia generado correctamente");
        return "historial-new_res";
    }

    // Metodo para listar todas las citas
    @GetMapping("/list")
    public Iterable<Historial> list() {
        return historialRepository.findAll();
    }

    // Metodo para editar una cita
    @PostMapping("/edit/{id}")
    public Historial update(@PathVariable int id, @ModelAttribute Historial historial, Model model) {
        Historial historialExistente = historialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el historial con el id: " + id));
        historialExistente.setFecha(historial.getFecha());
        historialExistente.setPaciente(historial.getPaciente());
        historialExistente.setOdontologo(historial.getOdontologo());
        historialExistente.setTratamiento(historial.getTratamiento());

        return historialRepository.save(historialExistente);
    }

    // Metodo para eliminar una cita
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        try {
            historialRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "redirect:/historiales/list-front";
    }

    // Metodo para buscar una cita por id
    @GetMapping("/find/{id}")
    public ResponseEntity<Historial> findById(@PathVariable int id) {
        try {
            Historial historial = historialRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No existe el historial con el id: " + id));
            return ResponseEntity.ok(historial);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/new")
    public String showAdd(Model model) {
        ArrayList<Tratamiento> tratamientos = (ArrayList<Tratamiento>) tratamientoRepository.findAll();
        model.addAttribute("tratamientos", tratamientos);
        ArrayList<Odontologo> odontologos = (ArrayList<Odontologo>) odontologoRepository.findAll();
        model.addAttribute("odontologos", odontologos);
        ArrayList<Paciente> pacientes = (ArrayList<Paciente>) pacienteRepository.findAll();
        model.addAttribute("pacientes", pacientes);
        return "historial-new";
    }

    // Mostrar lista de pacientes
    @GetMapping("/list-front")
    public String listFront(Model model) {
        ArrayList<Historial> lista = (ArrayList<Historial>) historialRepository.findAll();
        model.addAttribute("historiales", lista);
        return "historial-list";
    }

    // Mostrar panel de confirmación para eliminar un paciente
    @GetMapping("/delete-front/{id}")
    public String showDelete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "historial-delete_conf";
    }

    // Mostrar formulario para editar un paciente
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id, Model model) {
        Optional<Historial> historial = historialRepository.findById(id);
        if (historial.isPresent()) {
            model.addAttribute("historial", historial.get());
            ArrayList<Tratamiento> tratamientos = (ArrayList<Tratamiento>) tratamientoRepository.findAll();
            model.addAttribute("tratamientos", tratamientos);
            ArrayList<Odontologo> odontologos = (ArrayList<Odontologo>) odontologoRepository.findAll();
            model.addAttribute("odontologos", odontologos);
            ArrayList<Paciente> pacientes = (ArrayList<Paciente>) pacienteRepository.findAll();
            model.addAttribute("pacientes", pacientes);
            return "historial-edit";
        } else {
            return "redirect:/historiales/list-front";
        }
    }

    @GetMapping("/list-front/{cedula}")
    public String listFrontPaciente(@PathVariable int cedula, Model model) {
        Optional<Paciente> paciente = pacienteRepository.findById(cedula);
        if (paciente.isPresent()) {
            ArrayList<Historial> historiales = historialRepository.findByPaciente(paciente.get());
            model.addAttribute("historiales", historiales);
            return "historial-list";
        }
        return "redirect:/home";
    }

    @GetMapping("/ver/{id}")
    public String seeHistoria(@PathVariable int id, Model model) {
        Optional<Historial> historia = historialRepository.findById(id);
        if (historia.isPresent()) {
            model.addAttribute("historial", historia.get());
            return "historial-view";
        } else {
            return "redirect:/historiales/list-front";
        }
    }
}
