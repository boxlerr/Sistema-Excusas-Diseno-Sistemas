package com.empresa.excusas.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {
    private static final List<ProntuarioDTO> prontuarios = new ArrayList<>();
    private static int nextId = 1;

    @GetMapping
    public List<ProntuarioDTO> getAll() {
        return prontuarios;
    }

    @PostMapping
    public ProntuarioDTO create(@RequestBody ProntuarioDTO dto) {
        dto.setId(nextId++);
        prontuarios.add(dto);
        return dto;
    }

    // DTO interno para la iteración 1
    public static class ProntuarioDTO {
        private int id;
        private int empleadoLegajo;
        private String motivo;
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