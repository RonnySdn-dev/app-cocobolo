// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\cliente\service\ClienteService.java
package com.t2.cocobolo.cliente.service;

import com.t2.cocobolo.cliente.domain.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> listar();
    Cliente guardar(Cliente c);
}
