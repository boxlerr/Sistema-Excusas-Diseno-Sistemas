package com.empresa.excusas.service;

import com.empresa.excusas.model.Encargado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class EncargadoServiceTest {

    @Autowired
    private EncargadoService encargadoService;

    @Test
    void testCrearYRecuperarEncargado() {
        // Given
        Encargado.EncargadoDTO dto = new Encargado.EncargadoDTO();
        dto.setNombre("Test Encargado");
        dto.setEmail("test.encargado@test.com");
        dto.setTipo("RECEPCIONISTA");
        dto.setModo("NORMAL");

        // When
        Encargado.EncargadoDTO creado = encargadoService.create(dto);

        // Then
        assertThat(creado.getLegajo()).isGreaterThan(0);
        assertThat(creado.getNombre()).isEqualTo("Test Encargado");
        
        // Verificar que se puede recuperar
        Encargado.EncargadoDTO recuperado = encargadoService.findByLegajo(creado.getLegajo());
        assertThat(recuperado).isNotNull();
        assertThat(recuperado.getEmail()).isEqualTo("test.encargado@test.com");
    }

    @Test
    void testCambiarModo() {
        // Given
        Encargado.EncargadoDTO dto = new Encargado.EncargadoDTO();
        dto.setNombre("Test Encargado Modo");
        dto.setEmail("test.modo@test.com");
        dto.setTipo("SUPERVISOR");
        dto.setModo("NORMAL");
        
        Encargado.EncargadoDTO creado = encargadoService.create(dto);

        // When
        Encargado.EncargadoDTO actualizado = encargadoService.cambiarModo(creado.getLegajo(), "PRODUCTIVO");

        // Then
        assertThat(actualizado.getModo()).isEqualTo("PRODUCTIVO");
    }

    @Test
    void testEmailDuplicado() {
        // Given
        Encargado.EncargadoDTO dto1 = new Encargado.EncargadoDTO();
        dto1.setNombre("Encargado 1");
        dto1.setEmail("duplicado@test.com");
        dto1.setTipo("RECEPCIONISTA");
        dto1.setModo("NORMAL");
        
        encargadoService.create(dto1);

        Encargado.EncargadoDTO dto2 = new Encargado.EncargadoDTO();
        dto2.setNombre("Encargado 2");
        dto2.setEmail("duplicado@test.com"); // Email duplicado
        dto2.setTipo("SUPERVISOR");
        dto2.setModo("NORMAL");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            encargadoService.create(dto2);
        });
    }

    @Test
    void testObtenerTodos() {
        // When
        List<Encargado.EncargadoDTO> encargados = encargadoService.getAll();

        // Then
        assertThat(encargados).isNotEmpty(); // Debe tener al menos los del data.sql
        
        // Verificar que contiene los datos iniciales
        boolean anaExists = encargados.stream()
            .anyMatch(enc -> enc.getNombre().equals("Ana López") && enc.getLegajo() == 2001);
        assertThat(anaExists).isTrue();
    }
}
