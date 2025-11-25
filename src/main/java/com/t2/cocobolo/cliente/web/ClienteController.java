// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\cliente\web\ClienteController.java
package com.t2.cocobolo.cliente.web;

import com.t2.cocobolo.cliente.domain.Cliente;
import com.t2.cocobolo.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("clientes", service.listar());
        model.addAttribute("cliente", new Cliente());
        return "cliente/lista";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("clientes", service.listar());
            return "cliente/lista";
        }
        service.guardar(cliente);
        return "redirect:/clientes";
    }
}
