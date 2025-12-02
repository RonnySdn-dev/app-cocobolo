package com.t2.cocobolo.asesoria.service.impl;

import com.t2.cocobolo.asesoria.domain.Cita;
import com.t2.cocobolo.asesoria.repo.CitaRepository;
import com.t2.cocobolo.asesoria.service.CitaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository repo;

    public CitaServiceImpl(CitaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Cita> listarTodas() {
        return repo.findAll();
    }

    @Override
    public Cita agendar(Cita cita) {
        // Podrías validar aquí que no haya otra cita a la misma hora
        cita.setEstado("PENDIENTE");
        return repo.save(cita);
    }

    @Override
    public void cambiarEstado(Long id, String nuevoEstado) {
        repo.findById(id).ifPresent(cita -> {
            cita.setEstado(nuevoEstado);
            repo.save(cita);
        });
    }
}