package com.t2.cocobolo.nosotros;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistoriaController {

    private final HistoriaService servicio = new HistoriaService();

    // 1. Mostrar la pagina HTML
    @GetMapping("/historia")
    public String verHistoria() {
        return "historia"; // Busca el archivo historia.html
    }

    // 2. Recibir datos del formulario y guardar
    @PostMapping("/guardarHito")
    public String guardar(@RequestParam int anio, 
                          @RequestParam String titulo, 
                          @RequestParam String descripcion) {
        
        Hito nuevo = new Hito();
        nuevo.setAnio(anio);
        nuevo.setTitulo(titulo);
        nuevo.setDescripcion(descripcion);
        
        servicio.registrarHito(nuevo);
        
        return "redirect:/historia"; // Recarga la pagina
    }
} 
// <--- ¡ASEGÚRATE DE QUE ESTA LLAVE ESTÉ AQUÍ!