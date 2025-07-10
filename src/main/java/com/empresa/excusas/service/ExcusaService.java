package com.empresa.excusas.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Service
public class ExcusaService {
    
    private final List<ExcusaDTO> excusas = new ArrayList<>();
    private int nextId = 1;

    // ========== MÉTODOS EXISTENTES ==========
    
    public List<ExcusaDTO> getAll() {
        return excusas;
    }

    public ExcusaDTO create(ExcusaDTO dto) {
        if (dto.getMotivo() == null || dto.getEmpleadoLegajo() == 0) {
            throw new IllegalArgumentException("Motivo y legajo de empleado son obligatorios");
        }
        
        // Validar que el motivo sea válido (según enum)
        if (!isValidMotivo(dto.getMotivo())) {
            throw new IllegalArgumentException("Motivo no válido");
        }
        
        dto.setId(nextId++);
        dto.setEstado("PENDIENTE");
        dto.setFecha(LocalDate.now().toString()); // Fecha actual
        
        // Simular procesamiento por cadena de encargados
        procesarExcusa(dto);
        
        excusas.add(dto);
        return dto;
    }

    public List<ExcusaDTO> getByLegajo(int legajo) {
        List<ExcusaDTO> result = new ArrayList<>();
        for (ExcusaDTO e : excusas) {
            if (e.getEmpleadoLegajo() == legajo) {
                result.add(e);
            }
        }
        return result;
    }

    // ========== MÉTODOS NUEVOS PARA LOS ENDPOINTS FALTANTES ==========

    /**
     * Obtiene todas las excusas con filtros opcionales
     */
    public List<ExcusaDTO> getAllWithFilters(String fechaDesde, String fechaHasta, String motivo, String encargado) {
        return excusas.stream()
                .filter(excusa -> filtrarPorFecha(excusa, fechaDesde, fechaHasta))
                .filter(excusa -> filtrarPorMotivo(excusa, motivo))
                .filter(excusa -> filtrarPorEncargado(excusa, encargado))
                .collect(Collectors.toList());
    }

    /**
     * Busca excusas por legajo con filtros de fecha opcionales
     */
    public List<ExcusaDTO> buscarPorLegajoYFechas(int legajo, String fechaDesde, String fechaHasta) {
        return excusas.stream()
                .filter(excusa -> excusa.getEmpleadoLegajo() == legajo)
                .filter(excusa -> filtrarPorFecha(excusa, fechaDesde, fechaHasta))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las excusas rechazadas
     */
    public List<ExcusaDTO> getExcusasRechazadas() {
        return excusas.stream()
                .filter(excusa -> "RECHAZADA".equals(excusa.getEstado()))
                .collect(Collectors.toList());
    }

    /**
     * Elimina excusas anteriores a una fecha límite
     */
    public int eliminarExcusasAnterioresA(String fechaLimiteStr) {
        try {
            LocalDate fechaLimite = LocalDate.parse(fechaLimiteStr, DateTimeFormatter.ISO_LOCAL_DATE);
            
            List<ExcusaDTO> excusasAEliminar = excusas.stream()
                    .filter(excusa -> {
                        LocalDate fechaExcusa = LocalDate.parse(excusa.getFecha(), DateTimeFormatter.ISO_LOCAL_DATE);
                        return fechaExcusa.isBefore(fechaLimite);
                    })
                    .collect(Collectors.toList());
            
            // Eliminar las excusas de la lista
            excusas.removeAll(excusasAEliminar);
            
            return excusasAEliminar.size();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use YYYY-MM-DD");
        }
    }

    // ========== MÉTODOS AUXILIARES ==========

    private boolean filtrarPorFecha(ExcusaDTO excusa, String fechaDesde, String fechaHasta) {
        if (fechaDesde == null && fechaHasta == null) return true;
        
        try {
            LocalDate fechaExcusa = LocalDate.parse(excusa.getFecha(), DateTimeFormatter.ISO_LOCAL_DATE);
            
            if (fechaDesde != null) {
                LocalDate desde = LocalDate.parse(fechaDesde, DateTimeFormatter.ISO_LOCAL_DATE);
                if (fechaExcusa.isBefore(desde)) return false;
            }
            
            if (fechaHasta != null) {
                LocalDate hasta = LocalDate.parse(fechaHasta, DateTimeFormatter.ISO_LOCAL_DATE);
                if (fechaExcusa.isAfter(hasta)) return false;
            }
            
            return true;
        } catch (DateTimeParseException e) {
            return true; // Si hay error en el formato, no filtrar
        }
    }

    private boolean filtrarPorMotivo(ExcusaDTO excusa, String motivo) {
        if (motivo == null || motivo.trim().isEmpty()) return true;
        return excusa.getMotivo().toLowerCase().contains(motivo.toLowerCase());
    }

    private boolean filtrarPorEncargado(ExcusaDTO excusa, String encargado) {
        if (encargado == null || encargado.trim().isEmpty()) return true;
        return excusa.getEncargadoQueAcepto() != null && 
               excusa.getEncargadoQueAcepto().toLowerCase().contains(encargado.toLowerCase());
    }

    private boolean isValidMotivo(String motivo) {
        // Lista de motivos válidos según tu enum
        List<String> motivosValidos = Arrays.asList(
            "ENFERMEDAD", "FAMILIAR", "PERSONAL", "TRABAJO", "ESTUDIO", "OTRO"
        );
        return motivosValidos.contains(motivo.toUpperCase());
    }

    private void procesarExcusa(ExcusaDTO excusa) {
        // Simular el procesamiento por la cadena de encargados
        // Aquí deberías integrar con tu lógica de Chain of Responsibility
        
        Random random = new Random();
        boolean aceptada = random.nextBoolean(); // Simulación simple
        
        if (aceptada) {
            excusa.setEstado("ACEPTADA");
            excusa.setEncargadoQueAcepto("CEO"); // Simulación
        } else {
            excusa.setEstado("RECHAZADA");
            excusa.setEncargadoQueAcepto(null);
        }
    }

    // ========== DTO ACTUALIZADO ==========
    
    public static class ExcusaDTO {
        private int id;
        @Min(value = 1, message = "El legajo del empleado debe ser mayor a 0")
        private int empleadoLegajo;
        @NotBlank(message = "El motivo es obligatorio")
        private String motivo;
        private String estado;
        private String fecha;
        private String encargadoQueAcepto;
        private String observaciones;

        public ExcusaDTO() {}

        // Getters y Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getEmpleadoLegajo() { return empleadoLegajo; }
        public void setEmpleadoLegajo(int empleadoLegajo) { this.empleadoLegajo = empleadoLegajo; }

        public String getMotivo() { return motivo; }
        public void setMotivo(String motivo) { this.motivo = motivo; }

        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }

        public String getFecha() { return fecha; }
        public void setFecha(String fecha) { this.fecha = fecha; }

        public String getEncargadoQueAcepto() { return encargadoQueAcepto; }
        public void setEncargadoQueAcepto(String encargadoQueAcepto) { this.encargadoQueAcepto = encargadoQueAcepto; }

        public String getObservaciones() { return observaciones; }
        public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

        @Override
        public String toString() {
            return "ExcusaDTO{" +
                    "id=" + id +
                    ", empleadoLegajo=" + empleadoLegajo +
                    ", motivo='" + motivo + '\'' +
                    ", estado='" + estado + '\'' +
                    ", fecha='" + fecha + '\'' +
                    ", encargadoQueAcepto='" + encargadoQueAcepto + '\'' +
                    '}';
        }
    }
}
