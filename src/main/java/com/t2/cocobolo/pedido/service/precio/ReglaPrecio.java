// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\pedido\service\precio\ReglaPrecio.java
package com.t2.cocobolo.pedido.service.precio;

import com.t2.cocobolo.pedido.domain.Pedido;

import java.math.BigDecimal;

/** OCP: nuevas reglas se agregan implementando esta interfaz sin tocar la calculadora */
public interface ReglaPrecio {
    BigDecimal aplicar(Pedido pedido, BigDecimal totalActual);
    int orden(); // para priorizar reglas si deseas
}
