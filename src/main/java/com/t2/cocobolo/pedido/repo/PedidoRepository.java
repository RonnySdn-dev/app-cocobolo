// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\pedido\repo\PedidoRepository.java
package com.t2.cocobolo.pedido.repo;

import com.t2.cocobolo.pedido.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> { }
