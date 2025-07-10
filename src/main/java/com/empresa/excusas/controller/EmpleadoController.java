package com.empresa.excusas.controller;

import com.empresa.excusas.model.EmpleadoExcusador;
import com.empresa.excusas.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoExcusador>> obtenerTodosLosEmpleados() {
        List<EmpleadoExcusador> empleados = empleadoService.obtenerTodosLosEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @PostMapping
    public ResponseEntity<EmpleadoExcusador> crearEmpleado(@RequestBody CrearEmpleadoRequest request) {
        try {
            EmpleadoExcusador empleado = empleadoService.crearEmpleado(
                request.getNombre(), 
                request.getEmail(), 
                request.getLegajo()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(empleado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{legajo}")
    public ResponseEntity<EmpleadoExcusador> obtenerEmpleadoPorLegajo(@PathVariable int legajo) {
        return empleadoService.obtenerEmpleadoPorLegajo(legajo)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoExcusador> actualizarEmpleado(
            @PathVariable Long id, 
            @RequestBody ActualizarEmpleadoRequest request) {
        try {
            EmpleadoExcusador empleado = empleadoService.actualizarEmpleado(id, request.getNombre(), request.getEmail());
            return ResponseEntity.ok(empleado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        try {
            empleadoService.eliminarEmpleado(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EmpleadoExcusador>> buscarPorNombre(@RequestParam String nombre) {
        List<EmpleadoExcusador> empleados = empleadoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(empleados);
    }

    // DTOs para las requests
    public static class CrearEmpleadoRequest {
        private String nombre;
        private String email;
        private int legajo;
        
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public int getLegajo() { return legajo; }
        public void setLegajo(int legajo) { this.legajo = legajo; }
    }

    public static class ActualizarEmpleadoRequest {
        private String nombre;
        private String email;
        
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
