package com.empresa.excusas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    private static final List<EmpleadoDTO> empleados = new ArrayList<>();
    private static int nextLegajo = 1000;

    @GetMapping
    public List<EmpleadoDTO> getAll() {
        return empleados;
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> create(@RequestBody EmpleadoDTO dto) {
        if (dto.getNombre() == null || dto.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        dto.setLegajo(nextLegajo++);
        empleados.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{legajo}")
    public ResponseEntity<EmpleadoDTO> getByLegajo(@PathVariable int legajo) {
        return empleados.stream()
                .filter(e -> e.getLegajo() == legajo)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DTO interno para la iteración 1
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