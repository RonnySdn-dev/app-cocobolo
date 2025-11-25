// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\pedido\service\precio\CalculadoraTotalPedido.java
package com.t2.cocobolo.pedido.service.precio;

import com.t2.cocobolo.pedido.domain.ItemPedido;
import com.t2.cocobolo.pedido.domain.Pedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/** SRP: solo calcula totales. DIP: depende de la abstracción ReglaPrecio. */
@Component
public class CalculadoraTotalPedido {

    private final List<ReglaPrecio> reglas;

    public CalculadoraTotalPedido(List<ReglaPrecio> reglas) {
        this.reglas = reglas.stream()
                .sorted(Comparator.comparingInt(ReglaPrecio::orden))
                .toList();
    }

    public BigDecimal calcular(Pedido pedido) {
        // suma base
        BigDecimal base = pedido.getItems().stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // reglas OCP
        BigDecimal total = base;
        for (ReglaPrecio r : reglas) {
            total = r.aplicar(pedido, total);
        }
        return total;
    }
}
