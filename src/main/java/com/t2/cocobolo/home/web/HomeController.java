package com.t2.cocobolo.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
@GetMapping("/")
    public String bienvenido() {
        // Antes era "home", ahora debe ser "home/home"
        // El primer "home" es la carpeta, el segundo es el archivo.
        return "home/home"; 
    }
}