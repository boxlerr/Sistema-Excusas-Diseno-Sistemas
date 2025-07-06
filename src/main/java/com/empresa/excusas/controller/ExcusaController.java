package com.empresa.excusas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/excusas")
public class ExcusaController {
    private static final List<ExcusaDTO> excusas = new ArrayList<>();
    private static int nextId = 1;

    @GetMapping
    public List<ExcusaDTO> getAll() {
        return excusas;
    }

    @PostMapping
    public ResponseEntity<ExcusaDTO> create(@RequestBody ExcusaDTO dto) {
        if (dto.getMotivo() == null || dto.getEmpleadoLegajo() == 0) {
            return ResponseEntity.badRequest().build();
        }
        dto.setId(nextId++);
        dto.setEstado("PENDIENTE");
        excusas.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{legajo}")
    public List<ExcusaDTO> getByLegajo(@PathVariable int legajo) {
        List<ExcusaDTO> result = new ArrayList<>();
        for (ExcusaDTO e : excusas) {
            if (e.getEmpleadoLegajo() == legajo) {
                result.add(e);
            }
        }
        return result;
    }

    // DTO interno para la iteración 1
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