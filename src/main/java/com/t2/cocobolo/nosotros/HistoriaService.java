package com.t2.cocobolo.nosotros;

import org.springframework.stereotype.Service;

@Service
public class HistoriaService {

    private final HistoriaRepository repositorio = new HistoriaRepository();

    public void registrarHito(Hito hito) {
        // Validacion simple: Si el año es negativo, lo corregimos a 2024 (ejemplo)
        if (hito.getAnio() < 1900) {
            System.out.println("Error: Año inválido. Se ajustará a 2000.");
            hito.setAnio(2000);
        }
        
        // Llamamos al repositorio para guardar
        repositorio.guardar(hito);
    }
}