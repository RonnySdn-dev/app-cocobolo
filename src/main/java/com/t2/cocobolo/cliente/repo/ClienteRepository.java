// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\cliente\repo\ClienteRepository.java
package com.t2.cocobolo.cliente.repo;

import com.t2.cocobolo.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }
