package com.empresa.excusas.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.empresa.excusas.clases.service.EncargadoService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/encargados")
public class EncargadoController {
    private final EncargadoService encargadoService;

    @Autowired
    public EncargadoController(EncargadoService encargadoService) {
        this.encargadoService = encargadoService;
    }

    @GetMapping
    public List<EncargadoService.EncargadoDTO> getAll() {
        return encargadoService.getAll();
    }

    @PostMapping
    public EncargadoService.EncargadoDTO create(@RequestBody EncargadoService.EncargadoDTO dto) {
        return encargadoService.create(dto);
    }

    @PutMapping("/modo")
    public EncargadoService.EncargadoDTO cambiarModo(@RequestParam int legajo, @RequestParam String modo) {
        return encargadoService.cambiarModo(legajo, modo);
    }
} 