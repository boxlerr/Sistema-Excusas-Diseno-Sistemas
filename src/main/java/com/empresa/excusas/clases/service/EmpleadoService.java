package com.empresa.excusas.clases.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmpleadoService {
    private final List<EmpleadoDTO> empleados = new ArrayList<>();
    private int nextLegajo = 1000;

    public List<EmpleadoDTO> getAll() {
        return empleados;
    }

    public EmpleadoDTO create(EmpleadoDTO dto) {
        if (dto.getNombre() == null || dto.getEmail() == null) {
            throw new IllegalArgumentException("Nombre y email son obligatorios");
        }
        dto.setLegajo(nextLegajo++);
        empleados.add(dto);
        return dto;
    }

    public Optional<EmpleadoDTO> getByLegajo(int legajo) {
        return empleados.stream().filter(e -> e.getLegajo() == legajo).findFirst();
    }

    // DTO interno para la iteración 2
    public static class EmpleadoDTO {
        private String nombre;
        private String email;
        private int legajo;
        public EmpleadoDTO() {}
        public EmpleadoDTO(String nombre, String email, int legajo) {
            this.nombre = nombre;
            this.email = email;
            this.legajo = legajo;
        }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public int getLegajo() { return legajo; }
        public void setLegajo(int legajo) { this.legajo = legajo; }
    }
} 