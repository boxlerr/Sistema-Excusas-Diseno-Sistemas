package com.empresa.excusas.repository;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.EmpleadoExcusador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ExcusaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExcusaRepository excusaRepository;

    @Test
    void testGuardarYRecuperarExcusa() {
        // Given
        EmpleadoExcusador empleado = new EmpleadoExcusador("Test User", "test@test.com", 9999);
        entityManager.persistAndFlush(empleado);
        
        Excusa excusa = new Excusa(empleado, "Test excusa", "TRIVIAL");
        
        // When
        Excusa excusaGuardada = excusaRepository.save(excusa);
        entityManager.flush();
        
        // Then
        assertThat(excusaGuardada.getId()).isNotNull();
        assertThat(excusaGuardada.getDescripcion()).isEqualTo("Test excusa");
        assertThat(excusaGuardada.getTipoExcusa()).isEqualTo("TRIVIAL");
        assertThat(excusaGuardada.getEmpleado().getNombre()).isEqualTo("Test User");
    }

    @Test
    void testBuscarPorTipoExcusa() {
        // Given
        EmpleadoExcusador empleado = new EmpleadoExcusador("Test User", "test@test.com", 9998);
        entityManager.persistAndFlush(empleado);
        
        Excusa excusa1 = new Excusa(empleado, "Excusa trivial 1", "TRIVIAL");
        Excusa excusa2 = new Excusa(empleado, "Excusa trivial 2", "TRIVIAL");
        Excusa excusa3 = new Excusa(empleado, "Excusa compleja", "COMPLEJA");
        
        entityManager.persistAndFlush(excusa1);
        entityManager.persistAndFlush(excusa2);
        entityManager.persistAndFlush(excusa3);
        
        // When
        List<Excusa> excusasTriviales = excusaRepository.findByTipoExcusa("TRIVIAL");
        
        // Then
        assertThat(excusasTriviales).hasSize(2);
        assertThat(excusasTriviales).extracting(Excusa::getTipoExcusa)
                .containsOnly("TRIVIAL");
    }

    @Test
    void testBuscarPorEmpleadoLegajo() {
        // Given
        EmpleadoExcusador empleado1 = new EmpleadoExcusador("Empleado 1", "emp1@test.com", 9997);
        EmpleadoExcusador empleado2 = new EmpleadoExcusador("Empleado 2", "emp2@test.com", 9996);
        entityManager.persistAndFlush(empleado1);
        entityManager.persistAndFlush(empleado2);
        
        Excusa excusa1 = new Excusa(empleado1, "Excusa 1", "TRIVIAL");
        Excusa excusa2 = new Excusa(empleado1, "Excusa 2", "COMPLEJA");
        Excusa excusa3 = new Excusa(empleado2, "Excusa 3", "TRIVIAL");
        
        entityManager.persistAndFlush(excusa1);
        entityManager.persistAndFlush(excusa2);
        entityManager.persistAndFlush(excusa3);
        
        // When
        List<Excusa> excusasEmpleado1 = excusaRepository.findByEmpleadoLegajo(9997);
        
        // Then
        assertThat(excusasEmpleado1).hasSize(2);
        assertThat(excusasEmpleado1).extracting(excusa -> excusa.getEmpleado().getLegajo())
                .containsOnly(9997);
    }

    @Test
    void testContarExcusasPorEmpleado() {
        // Given
        EmpleadoExcusador empleado = new EmpleadoExcusador("Test User", "test@test.com", 9995);
        entityManager.persistAndFlush(empleado);
        
        entityManager.persistAndFlush(new Excusa(empleado, "Excusa 1", "TRIVIAL"));
        entityManager.persistAndFlush(new Excusa(empleado, "Excusa 2", "COMPLEJA"));
        entityManager.persistAndFlush(new Excusa(empleado, "Excusa 3", "MODERADA"));
        
        // When
        long count = excusaRepository.countByEmpleado(empleado);
        
        // Then
        assertThat(count).isEqualTo(3);
    }

    @Test
    void testBuscarPorEstado() {
        // Given
        EmpleadoExcusador empleado = new EmpleadoExcusador("Test User", "test@test.com", 9994);
        entityManager.persistAndFlush(empleado);
        
        Excusa excusa1 = new Excusa(empleado, "Excusa pendiente", "TRIVIAL");
        excusa1.setEstado("PENDIENTE");
        
        Excusa excusa2 = new Excusa(empleado, "Excusa aprobada", "TRIVIAL");
        excusa2.setEstado("APROBADA");
        
        entityManager.persistAndFlush(excusa1);
        entityManager.persistAndFlush(excusa2);
        
        // When
        List<Excusa> excusasPendientes = excusaRepository.findByEstado("PENDIENTE");
        
        // Then
        assertThat(excusasPendientes).hasSize(1);
        assertThat(excusasPendientes.get(0).getEstado()).isEqualTo("PENDIENTE");
    }
}
