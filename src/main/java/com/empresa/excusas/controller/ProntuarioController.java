package com.empresa.excusas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.empresa.excusas.service.ProntuarioService;

import java.util.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {
    private final ProntuarioService prontuarioService;

    @Autowired
    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    @GetMapping
    public ResponseEntity<List<ProntuarioService.ProntuarioDTO>> getAll() {
        List<ProntuarioService.ProntuarioDTO> prontuarios = prontuarioService.getAll();
        return ResponseEntity.ok(prontuarios);
    }

    @PostMapping
    public ResponseEntity<ProntuarioService.ProntuarioDTO> create(@Valid @RequestBody ProntuarioService.ProntuarioDTO dto) {
        ProntuarioService.ProntuarioDTO creado = prontuarioService.create(dto);
        return ResponseEntity.status(201).body(creado);
    }
} 