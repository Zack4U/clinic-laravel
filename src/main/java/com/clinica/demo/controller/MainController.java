package com.clinica.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}
