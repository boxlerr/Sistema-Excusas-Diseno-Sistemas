package com.empresa.excusas.repository;

import com.empresa.excusas.model.Prontuario;
import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.EmpleadoExcusador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProntuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Test
    void testGuardarYRecuperarProntuario() {
        // Given
        EmpleadoExcusador empleado = new EmpleadoExcusador("Test User", "test@test.com", 8888);
        entityManager.persistAndFlush(empleado);
        
        Excusa excusa = new Excusa(empleado, "Test excusa", "INVEROSIMIL");
        entityManager.persistAndFlush(excusa);
        
        Prontuario prontuario = new Prontuario("Test User", "test@test.com", 8888, excusa);
        
        // When
        Prontuario prontuarioGuardado = prontuarioRepository.save(prontuario);
        entityManager.flush();
        
        // Then
        assertThat(prontuarioGuardado.getId()).isNotNull();
        assertThat(prontuarioGuardado.getNombreEmpleado()).isEqualTo("Test User");
        assertThat(prontuarioGuardado.getLegajoEmpleado()).isEqualTo(8888);
        assertThat(prontuarioGuardado.getExcusa()).isNotNull();
    }

    @Test
    void testBuscarPorLegajoEmpleado() {
        // Given
        EmpleadoExcusador empleado = new EmpleadoExcusador("Test User", "test@test.com", 8887);
        entityManager.persistAndFlush(empleado);
        
        Excusa excusa1 = new Excusa(empleado, "Excusa 1", "INVEROSIMIL");
        Excusa excusa2 = new Excusa(empleado, "Excusa 2", "INVEROSIMIL");
        entityManager.persistAndFlush(excusa1);
        entityManager.persistAndFlush(excusa2);
        
        Prontuario prontuario1 = new Prontuario("Test User", "test@test.com", 8887, excusa1);
        Prontuario prontuario2 = new Prontuario("Test User", "test@test.com", 8887, excusa2);
        entityManager.persistAndFlush(prontuario1);
        entityManager.persistAndFlush(prontuario2);
        
        // When
        List<Prontuario> prontuarios = prontuarioRepository.findByLegajoEmpleado(8887);
        
        // Then
        assertThat(prontuarios).hasSize(2);
        assertThat(prontuarios).extracting(Prontuario::getLegajoEmpleado)
                .containsOnly(8887);
    }

    @Test
    void testContarProntuariosPorLegajo() {
        // Given
        EmpleadoExcusador empleado = new EmpleadoExcusador("Test User", "test@test.com", 8886);
        entityManager.persistAndFlush(empleado);
        
        Excusa excusa1 = new Excusa(empleado, "Excusa 1", "INVEROSIMIL");
        Excusa excusa2 = new Excusa(empleado, "Excusa 2", "INVEROSIMIL");
        Excusa excusa3 = new Excusa(empleado, "Excusa 3", "INVEROSIMIL");
        entityManager.persistAndFlush(excusa1);
        entityManager.persistAndFlush(excusa2);
        entityManager.persistAndFlush(excusa3);
        
        entityManager.persistAndFlush(new Prontuario("Test User", "test@test.com", 8886, excusa1));
        entityManager.persistAndFlush(new Prontuario("Test User", "test@test.com", 8886, excusa2));
        entityManager.persistAndFlush(new Prontuario("Test User", "test@test.com", 8886, excusa3));
        
        // When
        long count = prontuarioRepository.countByLegajoEmpleado(8886);
        
        // Then
        assertThat(count).isEqualTo(3);
    }

    @Test
    void testBuscarPorNombreEmpleadoContaining() {
        // Given
        EmpleadoExcusador empleado1 = new EmpleadoExcusador("Juan Pérez", "juan@test.com", 8885);
        EmpleadoExcusador empleado2 = new EmpleadoExcusador("Juan Carlos", "juanc@test.com", 8884);
        EmpleadoExcusador empleado3 = new EmpleadoExcusador("María García", "maria@test.com", 8883);
        
        entityManager.persistAndFlush(empleado1);
        entityManager.persistAndFlush(empleado2);
        entityManager.persistAndFlush(empleado3);
        
        Excusa excusa1 = new Excusa(empleado1, "Excusa 1", "INVEROSIMIL");
        Excusa excusa2 = new Excusa(empleado2, "Excusa 2", "INVEROSIMIL");
        Excusa excusa3 = new Excusa(empleado3, "Excusa 3", "INVEROSIMIL");
        
        entityManager.persistAndFlush(excusa1);
        entityManager.persistAndFlush(excusa2);
        entityManager.persistAndFlush(excusa3);
        
        entityManager.persistAndFlush(new Prontuario("Juan Pérez", "juan@test.com", 8885, excusa1));
        entityManager.persistAndFlush(new Prontuario("Juan Carlos", "juanc@test.com", 8884, excusa2));
        entityManager.persistAndFlush(new Prontuario("María García", "maria@test.com", 8883, excusa3));
        
        // When
        List<Prontuario> prontuarios = prontuarioRepository.findByNombreEmpleadoContaining("Juan");
        
        // Then
        assertThat(prontuarios).hasSize(2);
        assertThat(prontuarios).extracting(Prontuario::getNombreEmpleado)
                .containsExactlyInAnyOrder("Juan Pérez", "Juan Carlos");
    }
}
