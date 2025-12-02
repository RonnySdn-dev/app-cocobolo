package com.t2.cocobolo.asesoria.web;

import com.t2.cocobolo.asesoria.domain.Cita;
import com.t2.cocobolo.asesoria.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/citas")
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("citas", service.listarTodas());
        return "asesoria/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("cita", new Cita());
        return "asesoria/form";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute Cita cita, BindingResult result) {
        if (result.hasErrors()) {
            return "asesoria/form";
        }
        service.agendar(cita);
        return "redirect:/citas";
    }

    @PostMapping("/{id}/atender")
    public String marcarAtendida(@PathVariable Long id) {
        service.cambiarEstado(id, "ATENDIDA");
        return "redirect:/citas";
    }
    
    @PostMapping("/{id}/cancelar")
    public String cancelar(@PathVariable Long id) {
        service.cambiarEstado(id, "CANCELADA");
        return "redirect:/citas";
    }
}