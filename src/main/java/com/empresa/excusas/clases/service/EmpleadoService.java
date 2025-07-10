package com.empresa.excusas.clases.service;

import org.springframework.stereotype.Service;
import java.util.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

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
        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser válido")
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