package com.empresa.excusas.controller;

import org.springframework.web.bind.annotation.*;

import com.empresa.excusas.service.EncargadoService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/encargados")
public class EncargadoController {
    private final EncargadoService encargadoService;

    @Autowired
    public EncargadoController(EncargadoService encargadoService) {
        this.encargadoService = encargadoService;
    }

    @GetMapping
    public ResponseEntity<List<EncargadoService.EncargadoDTO>> getAll() {
        List<EncargadoService.EncargadoDTO> encargados = encargadoService.getAll();
        return ResponseEntity.ok(encargados);
    }

    @PostMapping
    public ResponseEntity<EncargadoService.EncargadoDTO> create(@Valid @RequestBody EncargadoService.EncargadoDTO dto) {
        EncargadoService.EncargadoDTO creado = encargadoService.create(dto);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/modo")
    public ResponseEntity<EncargadoService.EncargadoDTO> cambiarModo(@RequestParam int legajo, @RequestParam String modo) {
        EncargadoService.EncargadoDTO actualizado = encargadoService.cambiarModo(legajo, modo);
        return ResponseEntity.ok(actualizado);
    }
} 