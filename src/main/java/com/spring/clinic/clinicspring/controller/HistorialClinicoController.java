package com.spring.clinic.clinicspring.controller;

import com.spring.clinic.clinicspring.entity.HistorialClinico;
import com.spring.clinic.clinicspring.entity.Tratamiento;
import com.spring.clinic.clinicspring.repository.HistorialClinicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/historialClinico")
public class HistorialClinicoController {

    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    @GetMapping("/getAll")
    public Iterable<HistorialClinico> getAllHistorialClinico() {
        return historialClinicoRepository.findAll();
    }

    @PostMapping("/new")
    public String createHistorialClinico(@RequestBody List<Tratamiento> tratamientos,
            @RequestParam String alergias,
            @RequestParam String condicionesMedicas,
            @RequestParam String medicamentos) {

        HistorialClinico h = new HistorialClinico();
        h.setAlergias(alergias);
        h.setCondicionesMedicas(condicionesMedicas);
        h.setMedicamentos(medicamentos);
        h.setTratamientosRealizados(tratamientos);

        historialClinicoRepository.save(h);
        return "Listo";
    }

    @PatchMapping("/historialClinico/{id}")
    public String updateHistorialClinico(@PathVariable int id,
            @RequestBody HistorialClinico historialClinicoDetails) {

        Optional<HistorialClinico> optionalHistorialClinico = historialClinicoRepository.findById(id);

        if (optionalHistorialClinico.isPresent()) {
            HistorialClinico hc = optionalHistorialClinico.get();

            if (historialClinicoDetails.getTratamientosRealizados() != null) {
                hc.setTratamientosRealizados(historialClinicoDetails.getTratamientosRealizados());
            }
            if (historialClinicoDetails.getAlergias() != null) {
                hc.setAlergias(historialClinicoDetails.getAlergias());
            }
            if (historialClinicoDetails.getCondicionesMedicas() != null) {
                hc.setCondicionesMedicas(historialClinicoDetails.getCondicionesMedicas());
            }
            if (historialClinicoDetails.getMedicamentos() != null) {
                hc.setMedicamentos(historialClinicoDetails.getMedicamentos());
            }

            historialClinicoRepository.save(hc);
            return "Historial Clinico actualizado exitosamente";
        } else {
            return "El Historial Clinico con el ID proporcionado no existe";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteHistorialClinico(@PathVariable int id) {
        historialClinicoRepository.deleteById(id);
        return "Historial Clinico eliminado exitosamente";
    }
}