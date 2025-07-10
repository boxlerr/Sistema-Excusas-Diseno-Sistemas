package com.empresa.excusas.controller;

import com.empresa.excusas.model.Encargado;
import com.empresa.excusas.service.EncargadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/encargados")
@CrossOrigin(origins = "*")
public class EncargadoController {

    @Autowired
    private EncargadoService encargadoService;

    @GetMapping
    public ResponseEntity<List<Encargado.EncargadoDTO>> obtenerTodosLosEncargados() {
        List<Encargado.EncargadoDTO> encargados = encargadoService.getAll();
        return ResponseEntity.ok(encargados);
    }

    @PostMapping
    public ResponseEntity<Encargado.EncargadoDTO> crearEncargado(@RequestBody Encargado.EncargadoDTO encargadoDTO) {
        Encargado.EncargadoDTO creado = encargadoService.create(encargadoDTO);
        if (creado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{legajo}")
    public ResponseEntity<Encargado.EncargadoDTO> obtenerEncargadoPorLegajo(@PathVariable int legajo) {
        Encargado.EncargadoDTO encargado = encargadoService.findByLegajo(legajo);
        if (encargado != null) {
            return ResponseEntity.ok(encargado);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{legajo}/modo")
    public ResponseEntity<Encargado.EncargadoDTO> cambiarModo(@PathVariable int legajo, @RequestBody CambiarModoRequest request) {
        Encargado.EncargadoDTO encargadoActualizado = encargadoService.cambiarModo(legajo, request.getModo());
        if (encargadoActualizado != null) {
            return ResponseEntity.ok(encargadoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{legajo}")
    public ResponseEntity<Void> eliminarEncargado(@PathVariable int legajo) {
        try {
            encargadoService.deleteByLegajo(legajo);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DTO para cambiar modo
    public static class CambiarModoRequest {
        private String modo;
        
        public String getModo() { return modo; }
        public void setModo(String modo) { this.modo = modo; }
    }
}
