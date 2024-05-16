package com.clinica.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.clinica.demo.exceptions.ResourceNotFoundException;
import com.clinica.demo.model.Diente;
import com.clinica.demo.model.Odontograma;
import com.clinica.demo.model.Paciente;
import com.clinica.demo.model.odontograma.EstadoDiente;
import com.clinica.demo.model.odontograma.NotaDiente;
import com.clinica.demo.repository.DienteRepository;
import com.clinica.demo.repository.NotaDienteRepository;
import com.clinica.demo.repository.OdontogramaRepository;
import com.clinica.demo.repository.PacienteRepository;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/odontogramas")
public class OdontogramaController {

    @Autowired
    private OdontogramaRepository odontogramaRepository;

    @Autowired
    private DienteRepository dienteRepository;

    @Autowired
    private NotaDienteRepository notaDienteRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private TemplateEngine templateEngine;

    @Autowired
    public void PDFService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @PostMapping("/new")
    public String add(@ModelAttribute Odontograma odontograma, Model model) {
        Paciente paciente = pacienteRepository.findById(odontograma.getPaciente().getCedula())
                .orElse(null);
        odontograma.setPaciente(paciente);
        Date fechaNacimiento = paciente.getFechaNacimiento();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaNacimiento);
        int yearNacimiento = cal.get(Calendar.YEAR);
        int yearActual = Calendar.getInstance().get(Calendar.YEAR);
        int edadPaciente = yearActual - yearNacimiento;
        if (edadPaciente < 18) {
            odontograma.crearDientes("menor");
        } else {
            odontograma.crearDientes("mayor");
        }
        odontogramaRepository.save(odontograma);
        model.addAttribute("mensaje", "Odontograma creado correctamente");
        return "odontograma-new_res";
    }

    @GetMapping("/list")
    public Iterable<Odontograma> list() {
        return odontogramaRepository.findAll();
    }

    @GetMapping("/show/{id}")
    public Odontograma show(@PathVariable int id, @ModelAttribute Odontograma odontograma) {
        return odontogramaRepository.findById(odontograma.getId()).orElse(null);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        try {
            odontogramaRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "redirect:/odontogramas/list-front";
    }

    @GetMapping("/edit/{id}")
    public Odontograma edit(@PathVariable int id, @ModelAttribute Odontograma odontograma, Model model) {
        Odontograma odontogramaExistente = odontogramaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el historial con el id: " + id));
        return odontogramaRepository.save(odontogramaExistente);
    }

    @GetMapping("diente/{id}")
    public ResponseEntity<Diente> findDienteById(@PathVariable int id) {
        try {
            Diente odontologo = dienteRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No existe el diente con el id: " + id));
            return ResponseEntity.ok(odontologo);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/list-front")
    public String listFront(Model modelo) {
        modelo.addAttribute("odontogramas", odontogramaRepository.findAll());
        return "odontograma-list";
    }

    @GetMapping(path = "/list-front/{cedula}")
    public String listFront(@PathVariable int cedula, Model model) {
        Optional<Paciente> paciente = pacienteRepository.findById(cedula);
        if (paciente.isPresent()) {
            ArrayList<Odontograma> odontogramas = odontogramaRepository.findByPaciente(paciente.get());
            model.addAttribute("odontogramas", odontogramas);
            return "odontograma-list";
        }
        return "redirect:/home";
    }

    @GetMapping("/new")
    public String showAdd(Model model) {
        ArrayList<Paciente> pacientes = (ArrayList<Paciente>) pacienteRepository.findAll();
        model.addAttribute("pacientes", pacientes);
        return "odontograma-new";
    }

    @GetMapping("/ver/{id}")
    public String seeHistoria(@PathVariable int id, Model model) {
        Optional<Odontograma> odontograma = odontogramaRepository.findById(id);
        if (odontograma.isPresent()) {
            model.addAttribute("odontograma", odontograma.get());
            return "odontograma-view";
        } else {
            return "redirect:/odontogramas/list-front";
        }
    }

    @GetMapping("ver/{id_odo}/diente/{id}/nota/add")
    public String viewNotaDiente(@PathVariable int id, @PathVariable int id_odo, Model model) {
        Diente diente = dienteRepository.findById(id)
                .orElse(null);
        Odontograma odontograma = odontogramaRepository.findById(id_odo)
                .orElse(null);
        if (diente != null && odontograma != null) {
            model.addAttribute("diente", diente);
            model.addAttribute("odontograma", odontograma);
            return "odontograma-nota";
        } else {
            return null;
        }
    }

    @PostMapping("ver/{id_odo}/diente/{id}/nota/add")
    public Diente addNotaDiente(@PathVariable int id, @PathVariable int id_odo,
            @ModelAttribute NotaDiente nota, Model model) {
        Diente diente = dienteRepository.findById(id)
                .orElse(null);
        if (diente != null) {
            nota.setDiente(diente);
            nota.setFecha(Date.valueOf(LocalDate.now()));
            nota.setContenido(nota.getContenido());
            notaDienteRepository.save(nota);
            diente.addNota(nota);
            return dienteRepository.save(diente);
        } else {
            return null;
        }
    }

    @PostMapping("ver/{id_odo}/diente/{id}/diente/estado/change")
    public Diente cambiarEstadoDiente(@PathVariable int id, @PathVariable int id_odo,
            @RequestBody String estado, Model model) {
        Optional<Odontograma> odontograma = odontogramaRepository.findById(id_odo);
        Diente diente = dienteRepository.findById(id)
                .orElse(null);
        if (diente != null && odontograma != null) {
            diente.setEstado(EstadoDiente.valueOf(estado));
            model.addAttribute("odontograma", odontograma.get());
            return dienteRepository.save(diente);
        } else {
            return null;
        }
    }

    @GetMapping("ver/{id}/pdf")
    public void getOdontogramaPdf(@PathVariable int id, HttpServletResponse response) throws Exception {
        Optional<Odontograma> odontograma = odontogramaRepository.findById(id);
        if (!odontograma.isPresent()) {
            throw new Exception("Odontograma no encontrado");
        }

        Context context = new Context();
        context.setVariable("odontograma", odontograma.get());

        String html = templateEngine.process("odontograma-view-pdf", context);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=odontograma.pdf");

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);

        renderer.layout();
        renderer.createPDF(response.getOutputStream());
    }

    @GetMapping("/delete-front/{id}")
    public String showDelete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "odontograma-delete_conf";
    }
}
