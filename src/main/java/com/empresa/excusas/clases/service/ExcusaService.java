package com.empresa.excusas.clases.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExcusaService {
    private final List<ExcusaDTO> excusas = new ArrayList<>();
    private int nextId = 1;

    public List<ExcusaDTO> getAll() {
        return excusas;
    }

    public ExcusaDTO create(ExcusaDTO dto) {
        if (dto.getMotivo() == null || dto.getEmpleadoLegajo() == 0) {
            throw new IllegalArgumentException("Motivo y legajo de empleado son obligatorios");
        }
        dto.setId(nextId++);
        dto.setEstado("PENDIENTE");
        excusas.add(dto);
        return dto;
    }

    public List<ExcusaDTO> getByLegajo(int legajo) {
        List<ExcusaDTO> result = new ArrayList<>();
        for (ExcusaDTO e : excusas) {
            if (e.getEmpleadoLegajo() == legajo) {
                result.add(e);
            }
        }
        return result;
    }

    // DTO interno para la iteración 2
    public static class ExcusaDTO {
        private int id;
        private int empleadoLegajo;
        private String motivo;
        private String estado;
        public ExcusaDTO() {}
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public int getEmpleadoLegajo() { return empleadoLegajo; }
        public void setEmpleadoLegajo(int empleadoLegajo) { this.empleadoLegajo = empleadoLegajo; }
        public String getMotivo() { return motivo; }
        public void setMotivo(String motivo) { this.motivo = motivo; }
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
    }
} 