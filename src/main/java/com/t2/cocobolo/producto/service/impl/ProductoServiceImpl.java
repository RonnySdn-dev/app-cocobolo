// D:\Conf. de Software\cocobolo\src\main\java\com\t2\cocobolo\producto\service\impl\ProductoServiceImpl.java
package com.t2.cocobolo.producto.service.impl;

import com.t2.cocobolo.producto.domain.Producto;
import com.t2.cocobolo.producto.repo.ProductoRepository;
import com.t2.cocobolo.producto.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository repo;

    public ProductoServiceImpl(ProductoRepository repo) {
        this.repo = repo;
    }

    @Override public List<Producto> listar() { return repo.findAll(); }
    @Override public Optional<Producto> porId(Long id) { return repo.findById(id); }
    @Override public Producto guardar(Producto p) { return repo.save(p); }
    @Override public void alternarActivo(Long id) {
        repo.findById(id).ifPresent(p -> { p.setActivo(!p.isActivo()); repo.save(p); });
    }
}
