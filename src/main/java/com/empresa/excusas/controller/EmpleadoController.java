package com.empresa.excusas.controller;

import com.empresa.excusas.clases.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<EmpleadoService.EmpleadoDTO> getAll() {
        return empleadoService.getAll();
    }

    @PostMapping
    public ResponseEntity<EmpleadoService.EmpleadoDTO> create(@RequestBody EmpleadoService.EmpleadoDTO dto) {
        try {
            EmpleadoService.EmpleadoDTO creado = empleadoService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{legajo}")
    public ResponseEntity<EmpleadoService.EmpleadoDTO> getByLegajo(@PathVariable int legajo) {
        return empleadoService.getByLegajo(legajo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 