// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\producto\repo\ProductoRepository.java
package com.t2.cocobolo.producto.repo;

import com.t2.cocobolo.producto.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> { }
