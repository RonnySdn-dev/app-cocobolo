package com.t2.cocobolo.nosotros;

import java.util.List; // <--- Importante

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoriaService {

    @Autowired
    private HistoriaRepository repositorio;

    public void registrarHito(Hito hito) {
        if (hito.getAnio() < 1900) {
            hito.setAnio(2000); 
        }
        repositorio.guardar(hito);
    }

    public List<Hito> obtenerHistoria() {
        return repositorio.listarTodos();
    }
}