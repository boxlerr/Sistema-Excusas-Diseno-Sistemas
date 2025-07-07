package com.empresa.excusas.clases.service;

import org.springframework.stereotype.Service;
import java.util.*;

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
        private String nombre;
        private String email;
        private int legajo;
        private String tipo;
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