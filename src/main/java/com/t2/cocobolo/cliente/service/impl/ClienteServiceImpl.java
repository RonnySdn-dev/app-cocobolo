// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\cliente\service\impl\ClienteServiceImpl.java
package com.t2.cocobolo.cliente.service.impl;

import com.t2.cocobolo.cliente.domain.Cliente;
import com.t2.cocobolo.cliente.repo.ClienteRepository;
import com.t2.cocobolo.cliente.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repo;

    public ClienteServiceImpl(ClienteRepository repo) {
        this.repo = repo;
    }

    @Override public List<Cliente> listar() { return repo.findAll(); }
    @Override public Cliente guardar(Cliente c) { return repo.save(c); }
}
