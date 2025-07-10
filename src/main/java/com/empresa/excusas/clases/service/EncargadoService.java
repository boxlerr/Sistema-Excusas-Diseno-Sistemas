package com.empresa.excusas.clases.service;

import org.springframework.stereotype.Service;
import java.util.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Service
public class EncargadoService {
    private final List<EncargadoDTO> encargados = new ArrayList<>();
    private int nextLegajo = 2000;

    public List<EncargadoDTO> getAll() {
        return encargados;
    }

    public EncargadoDTO create(EncargadoDTO dto) {
        dto.setLegajo(nextLegajo++);
        encargados.add(dto);
        return dto;
    }

    public EncargadoDTO cambiarModo(int legajo, String modo) {
        for (EncargadoDTO e : encargados) {
            if (e.getLegajo() == legajo) {
                e.setModo(modo);
                return e;
            }
        }
        return null;
    }

    // DTO interno para la iteración 2
    public static class EncargadoDTO {
        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser válido")
        private String email;
        private int legajo;
        @NotBlank(message = "El tipo es obligatorio")
        private String tipo;
        @NotBlank(message = "El modo es obligatorio")
        private String modo;
        public EncargadoDTO() {}
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public int getLegajo() { return legajo; }
        public void setLegajo(int legajo) { this.legajo = legajo; }
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        public String getModo() { return modo; }
        public void setModo(String modo) { this.modo = modo; }
    }
} 