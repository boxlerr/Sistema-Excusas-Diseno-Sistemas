package com.empresa.excusas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.empresa.excusas.service.EmpleadoService;

import java.util.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoService.EmpleadoDTO>> getAll() {
        List<EmpleadoService.EmpleadoDTO> empleados = empleadoService.getAll();
        return ResponseEntity.ok(empleados);
    }

    @PostMapping
    public ResponseEntity<EmpleadoService.EmpleadoDTO> create(@Valid @RequestBody EmpleadoService.EmpleadoDTO dto) {
        try {
            EmpleadoService.EmpleadoDTO creado = empleadoService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<EmpleadoService.EmpleadoDTO> getByLegajo(@RequestParam int legajo) {
        return empleadoService.getByLegajo(legajo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 