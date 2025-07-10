package com.empresa.excusas.service;

import com.empresa.excusas.model.Prontuario;
import com.empresa.excusas.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public List<Prontuario> obtenerTodosLosProntuarios() {
        return prontuarioRepository.findAll();
    }

    public Prontuario crearProntuario(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }

    @Transactional(readOnly = true)
    public Optional<Prontuario> obtenerProntuarioPorId(Long id) {
        return prontuarioRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Prontuario> obtenerProntuariosPorLegajo(int legajo) {
        return prontuarioRepository.findByLegajoEmpleado(legajo);
    }

    @Transactional(readOnly = true)
    public List<Prontuario> obtenerProntuariosPorEmail(String email) {
        return prontuarioRepository.findByEmailEmpleado(email);
    }

    @Transactional(readOnly = true)
    public List<Prontuario> obtenerProntuariosPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        return prontuarioRepository.findByFechaCreacionBetween(inicio, fin);
    }

    @Transactional(readOnly = true)
    public List<Prontuario> buscarPorNombreEmpleado(String nombre) {
        return prontuarioRepository.findByNombreEmpleadoContaining(nombre);
    }

    @Transactional(readOnly = true)
    public long contarProntuariosPorLegajo(int legajo) {
        return prontuarioRepository.countByLegajoEmpleado(legajo);
    }

    public void eliminarProntuario(Long id) {
        if (!prontuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Prontuario no encontrado con ID: " + id);
        }
        prontuarioRepository.deleteById(id);
    }
}
