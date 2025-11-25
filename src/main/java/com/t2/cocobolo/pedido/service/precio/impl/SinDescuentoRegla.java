// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\pedido\service\precio\impl\SinDescuentoRegla.java
package com.t2.cocobolo.pedido.service.precio.impl;

import com.t2.cocobolo.pedido.domain.Pedido;
import com.t2.cocobolo.pedido.service.precio.ReglaPrecio;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/** Regla por defecto: no cambia el total */
@Component
public class SinDescuentoRegla implements ReglaPrecio {
    @Override public BigDecimal aplicar(Pedido pedido, BigDecimal totalActual) { return totalActual; }
    @Override public int orden() { return 1000; }
}
