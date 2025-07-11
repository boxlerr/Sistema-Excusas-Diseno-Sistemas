package com.empresa.excusas.service;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.EmpleadoExcusador;
import com.empresa.excusas.model.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.model.clasesAbstractas.TipoExcusa;
import com.empresa.excusas.repository.ExcusaRepository;
import com.empresa.excusas.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExcusaService {

    @Autowired
    private ExcusaRepository excusaRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EncargadoService encargadoService;

    public List<Excusa> obtenerTodasLasExcusas() {
        return excusaRepository.findAll();
    }

    public Excusa crearExcusa(int legajoEmpleado, String descripcion, String tipoExcusa) {
        // Validar tipo de excusa
        if (!TipoExcusa.isValid(tipoExcusa)) {
            throw new IllegalArgumentException("Tipo de excusa inválido: " + tipoExcusa);
        }
        // Buscar el empleado
        Optional<EmpleadoExcusador> empleadoOpt = empleadoRepository.findByLegajo(legajoEmpleado);
        if (!empleadoOpt.isPresent()) {
            throw new IllegalArgumentException("Empleado no encontrado con legajo: " + legajoEmpleado);
        }

        EmpleadoExcusador empleado = empleadoOpt.get();
        
        // Crear la excusa
        Excusa excusa = new Excusa(empleado, descripcion, tipoExcusa.toUpperCase());
        
        // Guardar en base de datos
        Excusa excusaGuardada = excusaRepository.save(excusa);
        
        // Procesar la excusa a través de la cadena de responsabilidad
        procesarExcusaConCadena(excusaGuardada);
        
        return excusaGuardada;
    }

    @Transactional(readOnly = true)
    public List<Excusa> obtenerExcusasPorLegajo(int legajo) {
        return excusaRepository.findByEmpleadoLegajo(legajo);
    }

    @Transactional(readOnly = true)
    public List<Excusa> obtenerExcusasPorTipo(String tipoExcusa) {
        return excusaRepository.findByTipoExcusa(tipoExcusa);
    }

    @Transactional(readOnly = true)
    public List<Excusa> obtenerExcusasPorEstado(String estado) {
        return excusaRepository.findByEstado(estado);
    }

    @Transactional(readOnly = true)
    public List<Excusa> obtenerExcusasPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        return excusaRepository.findByFechaCreacionBetween(inicio, fin);
    }

    @Transactional(readOnly = true)
    public long contarExcusasPorEmpleado(int legajo) {
        Optional<EmpleadoExcusador> empleadoOpt = empleadoRepository.findByLegajo(legajo);
        if (empleadoOpt.isPresent()) {
            return excusaRepository.countByEmpleado(empleadoOpt.get());
        }
        return 0;
    }

    public Excusa actualizarEstadoExcusa(Long id, String nuevoEstado) {
        Optional<Excusa> excusaOpt = excusaRepository.findById(id);
        if (excusaOpt.isPresent()) {
            Excusa excusa = excusaOpt.get();
            excusa.setEstado(nuevoEstado);
            return excusaRepository.save(excusa);
        }
        throw new IllegalArgumentException("Excusa no encontrada con ID: " + id);
    }

    private void procesarExcusaConCadena(Excusa excusa) {
        // Obtener la cadena de responsabilidad
        // EncargadoBase primerEncargado = encargadoService.crearCadenaDeResponsabilidad();
        // TODO: Implementar la lógica de la cadena de responsabilidad si es necesario
        System.out.println("🔄 Procesando excusa ID: " + excusa.getId() + " - " + excusa.getDescripcion());
        // Aquí iría la lógica de procesamiento con los patrones
    }
}
