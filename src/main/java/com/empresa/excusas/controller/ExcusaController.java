package com.empresa.excusas.controller;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.service.ExcusaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/excusas")
@CrossOrigin(origins = "*")
public class ExcusaController {

    @Autowired
    private ExcusaService excusaService;

    @GetMapping
    public ResponseEntity<List<Excusa>> obtenerTodasLasExcusas() {
        List<Excusa> excusas = excusaService.obtenerTodasLasExcusas();
        return ResponseEntity.ok(excusas);
    }

    @PostMapping
    public ResponseEntity<Excusa> crearExcusa(@RequestBody CrearExcusaRequest request) {
        try {
            Excusa excusa = excusaService.crearExcusa(
                request.getLegajoEmpleado(), 
                request.getDescripcion(), 
                request.getTipoExcusa()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(excusa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/empleado/{legajo}")
    public ResponseEntity<List<Excusa>> obtenerExcusasPorEmpleado(@PathVariable int legajo) {
        List<Excusa> excusas = excusaService.obtenerExcusasPorLegajo(legajo);
        return ResponseEntity.ok(excusas);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Excusa>> obtenerExcusasPorTipo(@PathVariable String tipo) {
        List<Excusa> excusas = excusaService.obtenerExcusasPorTipo(tipo);
        return ResponseEntity.ok(excusas);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Excusa>> obtenerExcusasPorEstado(@PathVariable String estado) {
        List<Excusa> excusas = excusaService.obtenerExcusasPorEstado(estado);
        return ResponseEntity.ok(excusas);
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Excusa>> obtenerExcusasPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        List<Excusa> excusas = excusaService.obtenerExcusasPorFecha(inicio, fin);
        return ResponseEntity.ok(excusas);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Excusa> actualizarEstado(@PathVariable Long id, @RequestBody ActualizarEstadoRequest request) {
        try {
            Excusa excusa = excusaService.actualizarEstadoExcusa(id, request.getEstado());
            return ResponseEntity.ok(excusa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/empleado/{legajo}/count")
    public ResponseEntity<Long> contarExcusasPorEmpleado(@PathVariable int legajo) {
        long count = excusaService.contarExcusasPorEmpleado(legajo);
        return ResponseEntity.ok(count);
    }

    // DTOs para las requests
    public static class CrearExcusaRequest {
        private int legajoEmpleado;
        private String descripcion;
        private String tipoExcusa;
        
        public int getLegajoEmpleado() { return legajoEmpleado; }
        public void setLegajoEmpleado(int legajoEmpleado) { this.legajoEmpleado = legajoEmpleado; }
        
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        
        public String getTipoExcusa() { return tipoExcusa; }
        public void setTipoExcusa(String tipoExcusa) { this.tipoExcusa = tipoExcusa; }
    }

    public static class ActualizarEstadoRequest {
        private String estado;
        
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
    }
}
