package com.t2.cocobolo.nosotros;

import org.springframework.beans.factory.annotation.Autowired; // <--- Importante
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistoriaController {

    @Autowired
    private HistoriaService servicio;

    @GetMapping("/historia")
    public String verHistoria(Model model) {
        model.addAttribute("listaHitos", servicio.obtenerHistoria());
        return "historia";
    }

    @PostMapping("/guardarHito")
    public String guardar(@RequestParam int anio, 
                          @RequestParam String titulo, 
                          @RequestParam String descripcion) {
        
        Hito nuevo = new Hito();
        nuevo.setAnio(anio);
        nuevo.setTitulo(titulo);
        nuevo.setDescripcion(descripcion);
        
        servicio.registrarHito(nuevo);
        
        return "redirect:/historia";
    }
}