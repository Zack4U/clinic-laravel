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
import com.clinica.demo.model.usuarios.Role;
import com.clinica.demo.model.usuarios.User;
import com.clinica.demo.repository.RoleRepository;
import com.clinica.demo.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Metodos del controlador

    // Metodo para agregar un odontologo
    @PostMapping("/new")
    public String add(@ModelAttribute User user, Model model) {
        userRepository.save(user);
        System.err.println(user);
        model.addAttribute("mensaje", "Usuario creado correctamente");
        return "user-new_res";
    }

    // Metodo para listar todos los odontologos
    @GetMapping("/list")
    public Iterable<User> list(Model modelo) {
        return userRepository.findAll();
    }

    // Actualizar un odontologo por id
    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute User user, Model model) {
        User userExistente = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el id: " + id));
        userExistente.setRole(user.getRole());

        userRepository.save(userExistente);
        model.addAttribute("mensaje", "Usuario editado correctamente");
        return "user-edit_res";
    }

    // Eliminar un odontologo por id
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "redirect:/users/list-front";
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findById(@PathVariable int id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el id: " + id));
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mostrar formulario de nuevo odontologo
    @GetMapping("/new")
    public String showAdd(Model modelo) {
        ArrayList<Role> roles = (ArrayList<Role>) roleRepository.findAll();
        modelo.addAttribute("roles", roles);
        return "user-new";
    }

    // Mostrar lista de odontologos
    @GetMapping(path = "/list-front")
    public String listFront(Model modelo) {
        ArrayList<User> lista = (ArrayList<User>) userRepository.findAll();
        ArrayList<Role> roles = (ArrayList<Role>) roleRepository.findAll();
        modelo.addAttribute("users", lista);
        modelo.addAttribute("roles", roles);
        System.err.println(lista);
        return "user-list";
    }

    // Mostrar panel de confirmación para eliminar un odontologo
    @GetMapping("/delete-front/{id}")
    public String showDelete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "user-delete_conf";
    }

    // Mostrar formulario para editar un odontologo
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id, Model model) {
        Optional<User> user = userRepository.findById(id);
        ArrayList<Role> roles = (ArrayList<Role>) roleRepository.findAll();
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("roles", roles);
            return "user-edit";
        } else {
            return "redirect:/users/list-front";
        }
    }
}