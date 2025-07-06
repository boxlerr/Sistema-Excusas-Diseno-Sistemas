package com.empresa.excusas.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/encargados")
public class EncargadoController {
    private static final List<EncargadoDTO> encargados = new ArrayList<>();
    private static int nextLegajo = 2000;

    @GetMapping
    public List<EncargadoDTO> getAll() {
        return encargados;
    }

    @PostMapping
    public EncargadoDTO create(@RequestBody EncargadoDTO dto) {
        dto.setLegajo(nextLegajo++);
        encargados.add(dto);
        return dto;
    }

    @PutMapping("/modo")
    public EncargadoDTO cambiarModo(@RequestParam int legajo, @RequestParam String modo) {
        for (EncargadoDTO e : encargados) {
            if (e.getLegajo() == legajo) {
                e.setModo(modo);
                return e;
            }
        }
        return null;
    }

    // DTO interno para la iteración 1
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