// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\pedido\service\impl\PedidoServiceImpl.java
package com.t2.cocobolo.pedido.service.impl;

import com.t2.cocobolo.cliente.domain.Cliente;
import com.t2.cocobolo.cliente.repo.ClienteRepository;
import com.t2.cocobolo.pedido.domain.ItemPedido;
import com.t2.cocobolo.pedido.domain.Pedido;
import com.t2.cocobolo.pedido.repo.ItemPedidoRepository;
import com.t2.cocobolo.pedido.repo.PedidoRepository;
import com.t2.cocobolo.pedido.service.PedidoService;
import com.t2.cocobolo.pedido.service.precio.CalculadoraTotalPedido;
import com.t2.cocobolo.producto.domain.Producto;
import com.t2.cocobolo.producto.repo.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ItemPedidoRepository itemRepo;
    private final ClienteRepository clienteRepo;
    private final ProductoRepository productoRepo;
    private final CalculadoraTotalPedido calculadora;

    public PedidoServiceImpl(PedidoRepository pedidoRepo, ItemPedidoRepository itemRepo,
                             ClienteRepository clienteRepo, ProductoRepository productoRepo,
                             CalculadoraTotalPedido calculadora) {
        this.pedidoRepo = pedidoRepo;
        this.itemRepo = itemRepo;
        this.clienteRepo = clienteRepo;
        this.productoRepo = productoRepo;
        this.calculadora = calculadora;
    }

    @Override public List<Pedido> listar() { return pedidoRepo.findAll(); }

    @Override public Optional<Pedido> porId(Long id) { return pedidoRepo.findById(id); }

    @Transactional
    @Override
    public Pedido crearPedido(Long clienteId, Long productoId, Integer cantidad) {
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no existe"));
        Producto producto = productoRepo.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no existe"));
        if (cantidad == null || cantidad < 1) throw new IllegalArgumentException("Cantidad inválida");

        // construir Pedido + Item (SRP: la lógica de total está en CalculadoraTotalPedido)
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);

        ItemPedido item = new ItemPedido();
        item.setProducto(producto);
        item.setCantidad(cantidad);
        item.setPrecioUnitario(producto.getPrecioUnitario());
        item.setSubtotal(producto.getPrecioUnitario().multiply(BigDecimal.valueOf(cantidad)));

        pedido.addItem(item);

        // calcular total aplicando reglas (OCP)
        pedido.setTotal(calculadora.calcular(pedido));

        // persistir
        Pedido guardado = pedidoRepo.save(pedido);
        itemRepo.saveAll(guardado.getItems());

        // ajustar stock simple
        producto.setStock(producto.getStock() - cantidad);
        productoRepo.save(producto);

        return guardado;
    }
}
