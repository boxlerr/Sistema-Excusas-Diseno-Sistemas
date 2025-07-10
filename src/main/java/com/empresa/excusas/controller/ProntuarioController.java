package com.empresa.excusas.controller;

import com.empresa.excusas.model.Prontuario;
import com.empresa.excusas.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
@CrossOrigin(origins = "*")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping
    public ResponseEntity<List<Prontuario>> obtenerTodosLosProntuarios() {
        List<Prontuario> prontuarios = prontuarioService.obtenerTodosLosProntuarios();
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> obtenerProntuarioPorId(@PathVariable Long id) {
        return prontuarioService.obtenerProntuarioPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empleado/{legajo}")
    public ResponseEntity<List<Prontuario>> obtenerProntuariosPorLegajo(@PathVariable int legajo) {
        List<Prontuario> prontuarios = prontuarioService.obtenerProntuariosPorLegajo(legajo);
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<Prontuario>> obtenerProntuariosPorEmail(@PathVariable String email) {
        List<Prontuario> prontuarios = prontuarioService.obtenerProntuariosPorEmail(email);
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Prontuario>> obtenerProntuariosPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        List<Prontuario> prontuarios = prontuarioService.obtenerProntuariosPorFecha(inicio, fin);
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Prontuario>> buscarPorNombre(@RequestParam String nombre) {
        List<Prontuario> prontuarios = prontuarioService.buscarPorNombreEmpleado(nombre);
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/empleado/{legajo}/count")
    public ResponseEntity<Long> contarProntuariosPorLegajo(@PathVariable int legajo) {
        long count = prontuarioService.contarProntuariosPorLegajo(legajo);
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProntuario(@PathVariable Long id) {
        try {
            prontuarioService.eliminarProntuario(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
