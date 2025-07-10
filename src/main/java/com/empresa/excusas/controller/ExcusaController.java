package com.empresa.excusas.controller;

import com.empresa.excusas.clases.service.ExcusaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
    public List<ExcusaService.ExcusaDTO> getAll(
            @RequestParam(required = false) String fechaDesde,
            @RequestParam(required = false) String fechaHasta,
            @RequestParam(required = false) String motivo,
            @RequestParam(required = false) String encargado) {
        return excusaService.getAllWithFilters(fechaDesde, fechaHasta, motivo, encargado);
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

    @GetMapping("/busqueda")
    public ResponseEntity<List<ExcusaService.ExcusaDTO>> buscarExcusas(
            @RequestParam int legajo,
            @RequestParam(required = false) String fechaDesde,
            @RequestParam(required = false) String fechaHasta) {
        
        try {
            List<ExcusaService.ExcusaDTO> excusas = excusaService.buscarPorLegajoYFechas(legajo, fechaDesde, fechaHasta);
            return ResponseEntity.ok(excusas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/rechazadas")
    public ResponseEntity<List<ExcusaService.ExcusaDTO>> getExcusasRechazadas() {
        List<ExcusaService.ExcusaDTO> excusasRechazadas = excusaService.getExcusasRechazadas();
        return ResponseEntity.ok(excusasRechazadas);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Map<String, Object>> eliminarExcusasPorFecha(
            @RequestParam(required = false) String fechaLimite) {
        

        if (fechaLimite == null || fechaLimite.trim().isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "El parámetro 'fechaLimite' es obligatorio");
            error.put("mensaje", "Para proteger contra eliminaciones accidentales, debe proporcionar una fecha límite");
            return ResponseEntity.badRequest().body(error);
        }

        try {
            int excusasEliminadas = excusaService.eliminarExcusasAnterioresA(fechaLimite);
            
            Map<String, Object> response = new HashMap<>();
            response.put("excusasEliminadas", excusasEliminadas);
            response.put("fechaLimite", fechaLimite);
            response.put("mensaje", "Operación completada exitosamente");
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Formato de fecha inválido");
            error.put("mensaje", "Use el formato YYYY-MM-DD para la fecha");
            return ResponseEntity.badRequest().body(error);
        }
    }
} 
