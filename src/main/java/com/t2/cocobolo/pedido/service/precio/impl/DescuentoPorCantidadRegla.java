// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\pedido\service\precio\impl\DescuentoPorCantidadRegla.java
package com.t2.cocobolo.pedido.service.precio.impl;

import com.t2.cocobolo.pedido.domain.ItemPedido;
import com.t2.cocobolo.pedido.domain.Pedido;
import com.t2.cocobolo.pedido.service.precio.ReglaPrecio;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/** Ejemplo OCP: si algún ítem tiene cantidad >= 5, 5% al total */
@Component
public class DescuentoPorCantidadRegla implements ReglaPrecio {
    @Override
    public BigDecimal aplicar(Pedido pedido, BigDecimal totalActual) {
        boolean aplica = pedido.getItems().stream().map(ItemPedido::getCantidad).anyMatch(q -> q != null && q >= 5);
        if (aplica) {
            return totalActual.multiply(new BigDecimal("0.95"));
        }
        return totalActual;
    }
    @Override public int orden() { return 10; }
}
