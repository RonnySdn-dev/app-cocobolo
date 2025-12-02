package com.t2.cocobolo.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Este es el "mapa" que le falta a tu aplicación
    @GetMapping({"/", "/home"})
    public String index() {
        return "home/home"; // Abre el archivo home.html que está en templates/home
    }
}