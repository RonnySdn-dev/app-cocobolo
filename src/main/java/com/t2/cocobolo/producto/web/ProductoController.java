// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\producto\web\ProductoController.java
package com.t2.cocobolo.producto.web;

import com.t2.cocobolo.producto.domain.Producto;
import com.t2.cocobolo.producto.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("productos", service.listar());
        return "producto/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Producto p = service.porId(id).orElseThrow(() -> new IllegalArgumentException("Producto no existe"));
        model.addAttribute("producto", p);
        return "producto/form";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute("producto") Producto producto, BindingResult br) {
        if (br.hasErrors()) return "producto/form";
        service.guardar(producto);
        return "redirect:/productos";
    }

    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id) {
        service.alternarActivo(id);
        return "redirect:/productos";
    }
}
