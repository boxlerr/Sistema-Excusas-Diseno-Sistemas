package com.empresa.excusas.service;

import com.empresa.excusas.model.EmpleadoExcusador;
import com.empresa.excusas.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<EmpleadoExcusador> obtenerTodosLosEmpleados() {
        return empleadoRepository.findAll();
    }

    public EmpleadoExcusador crearEmpleado(String nombre, String email, int legajo) {
        // Validar que no exista el legajo
        if (empleadoRepository.existsByLegajo(legajo)) {
            throw new IllegalArgumentException("Ya existe un empleado con legajo: " + legajo);
        }
        
        // Validar que no exista el email
        if (empleadoRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Ya existe un empleado con email: " + email);
        }

        EmpleadoExcusador empleado = new EmpleadoExcusador(nombre, email, legajo);
        return empleadoRepository.save(empleado);
    }

    @Transactional(readOnly = true)
    public Optional<EmpleadoExcusador> obtenerEmpleadoPorLegajo(int legajo) {
        return empleadoRepository.findByLegajo(legajo);
    }

    @Transactional(readOnly = true)
    public Optional<EmpleadoExcusador> obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<EmpleadoExcusador> obtenerEmpleadoPorEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }

    public EmpleadoExcusador actualizarEmpleado(Long id, String nombre, String email) {
        Optional<EmpleadoExcusador> empleadoOpt = empleadoRepository.findById(id);
        if (empleadoOpt.isPresent()) {
            EmpleadoExcusador empleado = empleadoOpt.get();
            
            // Validar email único si cambió
            if (!empleado.getEmail().equals(email) && empleadoRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("Ya existe un empleado con email: " + email);
            }
            
            empleado.setNombre(nombre);
            empleado.setEmail(email);
            return empleadoRepository.save(empleado);
        }
        throw new IllegalArgumentException("Empleado no encontrado con ID: " + id);
    }

    public void eliminarEmpleado(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new IllegalArgumentException("Empleado no encontrado con ID: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<EmpleadoExcusador> buscarPorNombre(String nombre) {
        return empleadoRepository.findByNombreContaining(nombre);
    }
}
