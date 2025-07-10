package com.empresa.excusas.controller;

import com.empresa.excusas.clases.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {
    private final ProntuarioService prontuarioService;

    @Autowired
    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    @GetMapping
    public List<ProntuarioService.ProntuarioDTO> getAll() {
        return prontuarioService.getAll();
    }

    @PostMapping
    public ProntuarioService.ProntuarioDTO create(@Valid @RequestBody ProntuarioService.ProntuarioDTO dto) {
        return prontuarioService.create(dto);
    }
} 