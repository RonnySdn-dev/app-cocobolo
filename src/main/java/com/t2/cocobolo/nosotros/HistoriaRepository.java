package com.t2.cocobolo.nosotros;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HistoriaRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate; // Esta es la conexión real a MySQL

    // Método para GUARDAR en MySQL
    public void guardar(Hito hito) {
        String sql = "INSERT INTO historia (anio, titulo, descripcion) VALUES (?, ?, ?)";
        
        jdbcTemplate.update(sql, hito.getAnio(), hito.getTitulo(), hito.getDescripcion());
        
        System.out.println("✅ Hito guardado en MySQL: " + hito.getTitulo());
    }

    // Método para LEER de MySQL
    public List<Hito> listarTodos() {
        String sql = "SELECT * FROM historia ORDER BY anio ASC";
        
        // Ejecutamos la consulta y convertimos los resultados a objetos Hito
        List<Map<String, Object>> filas = jdbcTemplate.queryForList(sql);
        List<Hito> lista = new ArrayList<>();
        
        for (Map<String, Object> fila : filas) {
            Hito h = new Hito();
            h.setAnio((Integer) fila.get("anio"));
            h.setTitulo((String) fila.get("titulo"));
            h.setDescripcion((String) fila.get("descripcion"));
            lista.add(h);
        }
        
        return lista;
    }
}