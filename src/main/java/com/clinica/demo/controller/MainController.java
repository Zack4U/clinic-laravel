package com.clinica.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String goIndex() {
        return "index";
    }

    @GetMapping("/home")
    public String goIndex2() {
        return "index";
    }

    @GetMapping("/login")
    public String goLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String goSignup() {
        return "register";
    }
}
