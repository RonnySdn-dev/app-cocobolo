package com.t2.cocobolo.asesoria.service;

import com.t2.cocobolo.asesoria.domain.Cita;
import java.util.List;

public interface CitaService {
    List<Cita> listarTodas();
    Cita agendar(Cita cita);
    void cambiarEstado(Long id, String nuevoEstado);
}