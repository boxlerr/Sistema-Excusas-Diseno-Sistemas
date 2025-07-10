package com.empresa.excusas.service;

import org.springframework.stereotype.Service;
import java.util.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Service
public class ProntuarioService {
    private final List<ProntuarioDTO> prontuarios = new ArrayList<>();
    private int nextId = 1;

    public List<ProntuarioDTO> getAll() {
        return prontuarios;
    }

    public ProntuarioDTO create(ProntuarioDTO dto) {
        dto.setId(nextId++);
        prontuarios.add(dto);
        return dto;
    }

    // DTO interno para la iteración 2
    public static class ProntuarioDTO {
        private int id;
        @Min(value = 1, message = "El legajo del empleado debe ser mayor a 0")
        private int empleadoLegajo;
        @NotBlank(message = "El motivo es obligatorio")
        private String motivo;
        @NotBlank(message = "La fecha es obligatoria")
        private String fecha;
        public ProntuarioDTO() {}
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public int getEmpleadoLegajo() { return empleadoLegajo; }
        public void setEmpleadoLegajo(int empleadoLegajo) { this.empleadoLegajo = empleadoLegajo; }
        public String getMotivo() { return motivo; }
        public void setMotivo(String motivo) { this.motivo = motivo; }
        public String getFecha() { return fecha; }
        public void setFecha(String fecha) { this.fecha = fecha; }
    }
} 