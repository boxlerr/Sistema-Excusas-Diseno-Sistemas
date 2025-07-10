package com.empresa.excusas.clases;

import org.junit.jupiter.api.Test;

import com.empresa.excusas.model.EmpleadoExcusador;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para EmpleadoExcusador")
public class EmpleadoExcusadorTest {

    @Test
    @DisplayName("Debería crear un EmpleadoExcusador correctamente")
    public void testCreacionEmpleadoExcusador() {
        // Arrange & Act
        EmpleadoExcusador excusador = new EmpleadoExcusador("Axel", "axel@empresa.com", 1001);

        // Assert
        assertEquals("Axel", excusador.getNombre());
        assertEquals("axel@empresa.com", excusador.getEmail());
        assertEquals(1001, excusador.getLegajo());
    }

    @Test
    @DisplayName("Debería permitir modificar datos del EmpleadoExcusador")
    public void testModificarEmpleadoExcusador() {
        // Arrange
        EmpleadoExcusador excusador = new EmpleadoExcusador("Axel", "axel@empresa.com", 1001);

        // Act
        excusador.setNombre("Axel Modificado");
        excusador.setEmail("axel.mod@empresa.com");
        excusador.setLegajo(1002);

        // Assert
        assertEquals("Axel Modificado", excusador.getNombre());
        assertEquals("axel.mod@empresa.com", excusador.getEmail());
        assertEquals(1002, excusador.getLegajo());
    }

    @Test
    @DisplayName("Debería generar toString con información completa")
    public void testToString() {
        // Arrange
        EmpleadoExcusador excusador = new EmpleadoExcusador("Axel", "axel@empresa.com", 1001);

        // Act
        String resultado = excusador.toString();

        // Assert
        assertTrue(resultado.contains("Axel"));
        assertTrue(resultado.contains("axel@empresa.com"));
        assertTrue(resultado.contains("1001"));
    }
}
