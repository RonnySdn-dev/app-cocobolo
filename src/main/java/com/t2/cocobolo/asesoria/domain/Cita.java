package com.t2.cocobolo.asesoria.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "cita_diseno")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la mamá/papá es obligatorio")
    private String nombreCliente;

    @NotBlank
    private String telefono;

    @NotNull
    @Future(message = "La cita debe ser en el futuro")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaHora;

    private String preferenciaDiseno; // Ej: "Ositos", "Estrellas", "Minimalista"

    private String estado = "PENDIENTE"; // PENDIENTE, ATENDIDA, CANCELADA

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getPreferenciaDiseno() { return preferenciaDiseno; }
    public void setPreferenciaDiseno(String preferenciaDiseno) { this.preferenciaDiseno = preferenciaDiseno; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}