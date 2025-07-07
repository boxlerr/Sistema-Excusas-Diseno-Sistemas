package com.empresa.excusas.controller;

import com.empresa.excusas.clases.service.ExcusaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/excusas")
public class ExcusaController {
    private final ExcusaService excusaService;

    @Autowired
    public ExcusaController(ExcusaService excusaService) {
        this.excusaService = excusaService;
    }

    @GetMapping
    public List<ExcusaService.ExcusaDTO> getAll() {
        return excusaService.getAll();
    }

    @PostMapping
    public ResponseEntity<ExcusaService.ExcusaDTO> create(@RequestBody ExcusaService.ExcusaDTO dto) {
        try {
            ExcusaService.ExcusaDTO creada = excusaService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{legajo}")
    public List<ExcusaService.ExcusaDTO> getByLegajo(@PathVariable int legajo) {
        return excusaService.getByLegajo(legajo);
    }
} 