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
import com.clinica.demo.model.Cita;
import com.clinica.demo.model.Odontologo;
import com.clinica.demo.model.Paciente;
import com.clinica.demo.model.TipoTratamiento;
import com.clinica.demo.repository.CitaRepository;
import com.clinica.demo.repository.OdontologoRepository;
import com.clinica.demo.repository.PacienteRepository;
import com.clinica.demo.repository.TipoTratamientoRepository;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private TipoTratamientoRepository tipoTratamientoRepository;

    // Metodos del controlador

    // Metodo para agregar una cita
    @PostMapping("/new")
    public String add(@ModelAttribute Cita cita, Model model) {
        TipoTratamiento tipoTratamiento = tipoTratamientoRepository.findById(cita.getTipoTratamiento().getId())
                .orElse(null);
        cita.setTipoTratamiento(tipoTratamiento);
        Odontologo odontologo = odontologoRepository.findById(cita.getOdontologo().getId())
                .orElse(null);
        cita.setOdontologo(odontologo);
        Paciente paciente = pacienteRepository.findById(cita.getPaciente().getCedula())
                .orElse(null);
        cita.setPaciente(paciente);
        citaRepository.save(cita);
        model.addAttribute("mensaje", "Cita creado correctamente");
        return "cita-new_res";
    }

    // Metodo para listar todas las citas
    @GetMapping("/list")
    public Iterable<Cita> list() {
        return citaRepository.findAll();
    }

    // Metodo para editar una cita
    @PostMapping("/edit/{id}")
    public Cita update(@PathVariable int id, @ModelAttribute Cita cita, Model model) {
        Cita citaExistente = citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la cita con el id: " + id));
        citaExistente.setFecha(cita.getFecha());
        citaExistente.setHora(cita.getHora());
        citaExistente.setPaciente(cita.getPaciente());
        citaExistente.setOdontologo(cita.getOdontologo());
        citaExistente.setTipoTratamiento(cita.getTipoTratamiento());
        citaExistente.setEstado(cita.getEstado());

        return citaRepository.save(citaExistente);
    }

    // Metodo para eliminar una cita
    @PostMapping("/delete/{id}")
    public void delete(@PathVariable int id) {

    }

    // Metodo para buscar una cita por id
    @GetMapping("/find/{id}")
    public ResponseEntity<Cita> findById(@PathVariable int id) {
        try {
            Cita cita = citaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No existe la cita con el id: " + id));
            return ResponseEntity.ok(cita);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/new")
    public String showAdd(Model model) {
        ArrayList<TipoTratamiento> tiposTratamientos = (ArrayList<TipoTratamiento>) tipoTratamientoRepository.findAll();
        model.addAttribute("tiposTratamientos", tiposTratamientos);
        ArrayList<Odontologo> odontologos = (ArrayList<Odontologo>) odontologoRepository.findAll();
        model.addAttribute("odontologos", odontologos);
        ArrayList<Paciente> pacientes = (ArrayList<Paciente>) pacienteRepository.findAll();
        model.addAttribute("pacientes", pacientes);
        return "cita-new";
    }

    // Mostrar lista de pacientes
    @GetMapping("/list-front")
    public String listFront(Model model) {
        ArrayList<Cita> lista = (ArrayList<Cita>) citaRepository.findAll();
        model.addAttribute("citas", lista);
        return "cita-list";
    }

    // Mostrar panel de confirmación para eliminar un paciente
    @GetMapping("/delete-front/{id}")
    public String showDelete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "cita-delete_conf";
    }

    // Mostrar formulario para editar un paciente
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id, Model model) {
        Optional<Cita> cita = citaRepository.findById(id);
        if (cita.isPresent()) {
            model.addAttribute("cita", cita.get());
            ArrayList<TipoTratamiento> tratamientos = (ArrayList<TipoTratamiento>) tipoTratamientoRepository.findAll();
            model.addAttribute("tratamientos", tratamientos);
            ArrayList<Odontologo> odontologos = (ArrayList<Odontologo>) odontologoRepository.findAll();
            model.addAttribute("odontologos", odontologos);
            ArrayList<Paciente> pacientes = (ArrayList<Paciente>) pacienteRepository.findAll();
            model.addAttribute("pacientes", pacientes);
            return "cita-edit";
        } else {
            return "redirect:/citas/list-front";
        }
    }

}
