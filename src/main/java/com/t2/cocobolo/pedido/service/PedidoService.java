// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\pedido\service\PedidoService.java
package com.t2.cocobolo.pedido.service;

import com.t2.cocobolo.pedido.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> listar();
    Optional<Pedido> porId(Long id);
    Pedido crearPedido(Long clienteId, Long productoId, Integer cantidad);
}
