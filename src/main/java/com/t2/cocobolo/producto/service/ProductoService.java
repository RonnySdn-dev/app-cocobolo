// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\producto\service\ProductoService.java
package com.t2.cocobolo.producto.service;

import com.t2.cocobolo.producto.domain.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> porId(Long id);
    Producto guardar(Producto p);
    void alternarActivo(Long id);
}
