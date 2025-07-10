package com.empresa.excusas.service;

import com.empresa.excusas.model.Encargado;
import com.empresa.excusas.repository.EncargadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EncargadoService {

    @Autowired
    private EncargadoRepository encargadoRepository;

    private int nextLegajo = 2000;

    public List<Encargado.EncargadoDTO> getAll() {
        List<Encargado> encargados = encargadoRepository.findAll();
        return encargados.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Encargado.EncargadoDTO create(Encargado.EncargadoDTO dto) {
        // Validar que no exista el email
        if (encargadoRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Ya existe un encargado con email: " + dto.getEmail());
        }

        // Obtener el siguiente legajo disponible
        Integer maxLegajo = encargadoRepository.findMaxLegajo();
        if (maxLegajo != null && maxLegajo >= nextLegajo) {
            nextLegajo = maxLegajo + 1;
        }
        dto.setLegajo(nextLegajo++);

        // Validar que no exista el legajo (por si acaso)
        if (encargadoRepository.existsByLegajo(dto.getLegajo())) {
            throw new IllegalArgumentException("Ya existe un encargado con legajo: " + dto.getLegajo());
        }

        // Convertir DTO a entidad y guardar
        Encargado encargado = convertToEntity(dto);
        Encargado encargadoGuardado = encargadoRepository.save(encargado);
        
        return convertToDTO(encargadoGuardado);
    }

    public Encargado.EncargadoDTO cambiarModo(int legajo, String modo) {
        Optional<Encargado> encargadoOpt = encargadoRepository.findByLegajo(legajo);
        if (encargadoOpt.isPresent()) {
            Encargado encargado = encargadoOpt.get();
            encargado.setModo(modo);
            Encargado encargadoActualizado = encargadoRepository.save(encargado);
            return convertToDTO(encargadoActualizado);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Encargado.EncargadoDTO findByLegajo(int legajo) {
        Optional<Encargado> encargadoOpt = encargadoRepository.findByLegajo(legajo);
        return encargadoOpt.map(this::convertToDTO).orElse(null);
    }

    @Transactional(readOnly = true)
    public Encargado.EncargadoDTO findByEmail(String email) {
        Optional<Encargado> encargadoOpt = encargadoRepository.findByEmail(email);
        return encargadoOpt.map(this::convertToDTO).orElse(null);
    }

    public void deleteByLegajo(int legajo) {
        Optional<Encargado> encargadoOpt = encargadoRepository.findByLegajo(legajo);
        if (encargadoOpt.isPresent()) {
            encargadoRepository.delete(encargadoOpt.get());
        } else {
            throw new IllegalArgumentException("Encargado no encontrado con legajo: " + legajo);
        }
    }

    // Métodos de conversión
    private Encargado.EncargadoDTO convertToDTO(Encargado encargado) {
        return new Encargado.EncargadoDTO(
            encargado.getId(),
            encargado.getNombre(),
            encargado.getEmail(),
            encargado.getLegajo(),
            encargado.getTipo(),
            encargado.getModo()
        );
    }

    private Encargado convertToEntity(Encargado.EncargadoDTO dto) {
        return new Encargado(
            dto.getNombre(),
            dto.getEmail(),
            dto.getLegajo(),
            dto.getTipo(),
            dto.getModo()
        );
    }
}
