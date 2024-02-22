package com.spring.clinic.clinicspring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.clinic.clinicspring.entity.Odontologo;
import com.spring.clinic.clinicspring.repository.OdontologoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(path = "/odontologo")
public class OdontologoController {

    @Autowired
    private OdontologoRepository odontologoRepository;

    @PostMapping(path = "/new")
    public @ResponseBody String nuevo(@RequestParam String nombres, @RequestParam String apellidos,
            @RequestParam String especialidad, @RequestParam String telefono, @RequestParam String email) {
        Odontologo o = new Odontologo();
        o.setNombres(nombres);
        o.setApellidos(apellidos);
        o.setEspecialidad(especialidad);
        o.setTelefono(telefono);
        o.setEmail(email);

        odontologoRepository.save(o);
        return "Listo";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String eliminar(@RequestParam int id) {
        if (odontologoRepository.existsById(id)) {
            odontologoRepository.deleteById(id);
            return "Odontólogo eliminado exitosamente";
        } else {
            return "El odontólogo con el ID proporcionado no existe";
        }
    }

    @GetMapping(path = "/Especialidad")
    public @ResponseBody Iterable<Odontologo> listarPorEspecialidad(@RequestParam("Especialidad") String Especialidad) {
        return odontologoRepository.findByEspecialidad(Especialidad);
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String actualizar(@RequestParam int id, @RequestParam(required = false) String nombres,
            @RequestParam(required = false) String apellidos, @RequestParam(required = false) String especialidad,
            @RequestParam(required = false) String telefono, @RequestParam(required = false) String email) {

        Optional<Odontologo> optionalOdontologo = odontologoRepository.findById(id);

        if (optionalOdontologo.isPresent()) {
            Odontologo o = optionalOdontologo.get();

            if (nombres != null) {
                o.setNombres(nombres);
            }
            if (apellidos != null) {
                o.setApellidos(apellidos);
            }
            if (especialidad != null) {
                o.setEspecialidad(especialidad);
            }
            if (telefono != null) {
                o.setTelefono(telefono);
            }
            if (email != null) {
                o.setEmail(email);
            }

            odontologoRepository.save(o);
            return "Odontólogo actualizado exitosamente";
        } else {
            return "El odontólogo con el ID proporcionado no existe";
        }
    }

}
