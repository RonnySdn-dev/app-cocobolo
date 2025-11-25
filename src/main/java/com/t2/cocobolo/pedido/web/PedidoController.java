// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\pedido\web\PedidoController.java
package com.t2.cocobolo.pedido.web;

import com.t2.cocobolo.cliente.service.ClienteService;
import com.t2.cocobolo.pedido.service.PedidoService;
import com.t2.cocobolo.producto.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final ProductoService productoService;

    public PedidoController(PedidoService pedidoService,
                            ClienteService clienteService,
                            ProductoService productoService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.productoService = productoService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        return "pedido/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("productos", productoService.listar());
        return "pedido/form";
    }

    @PostMapping
    public String crear(@RequestParam Long clienteId,
                        @RequestParam Long productoId,
                        @RequestParam Integer cantidad) {
        pedidoService.crearPedido(clienteId, productoId, cantidad);
        return "redirect:/pedidos";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        var pedido = pedidoService.porId(id).orElseThrow(() -> new IllegalArgumentException("Pedido no existe"));
        model.addAttribute("pedido", pedido);
        return "pedido/detalle";
    }
}
