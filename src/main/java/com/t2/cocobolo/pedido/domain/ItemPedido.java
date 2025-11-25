// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\pedido\domain\ItemPedido.java
package com.t2.cocobolo.pedido.domain;

import com.t2.cocobolo.producto.domain.Producto;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Pedido pedido;

    @ManyToOne(optional=false)
    private Producto producto;

    private Integer cantidad;

    @Column(name="precio_unitario", precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    @Column(precision = 12, scale = 2)
    private BigDecimal subtotal;

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}
