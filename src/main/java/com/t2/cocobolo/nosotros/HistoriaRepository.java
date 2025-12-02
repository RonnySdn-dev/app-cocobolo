package com.t2.cocobolo.nosotros;

import org.springframework.stereotype.Repository;

@Repository
public class HistoriaRepository {
    
    public void guardar(Hito hito) {
        // Simulación de guardado en Base de Datos (SQL)
        System.out.println("=================================");
        System.out.println(" GUARDANDO EN BASE DE DATOS: ");
        System.out.println(" INSERT INTO historia (anio, titulo, descripcion) ");
        System.out.println(" VALUES (" + hito.getAnio() + ", '" + hito.getTitulo() + "', '" + hito.getDescripcion() + "');");
        System.out.println("=================================");
    }
}