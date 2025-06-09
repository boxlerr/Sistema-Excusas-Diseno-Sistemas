package com.empresa.excusas.clasesAbstractas;

import com.empresa.excusas.clasesAbstractas.Empleado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para Empleado")
public class EmpleadoTest {

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        empleado = new Empleado("Juan Pérez", "juan@empresa.com", 1001);
    }

    @Test
    @DisplayName("Debería crear un empleado con datos correctos")
    public void testCreacionEmpleado() {
        // Assert
        assertEquals("Juan Pérez", empleado.getNombre());
        assertEquals("juan@empresa.com", empleado.getEmail());
        assertEquals(1001, empleado.getLegajo());
    }

    @Test
    @DisplayName("Debería permitir modificar el nombre del empleado")
    public void testModificarNombre() {
        // Act
        empleado.setNombre("Pedro González");

        // Assert
        assertEquals("Pedro González", empleado.getNombre());
    }

    @Test
    @DisplayName("Debería generar toString con información completa")
    public void testToString() {
        // Act
        String resultado = empleado.toString();

        // Assert
        assertTrue(resultado.contains("Juan Pérez"));
        assertTrue(resultado.contains("juan@empresa.com"));
        assertTrue(resultado.contains("1001"));
    }
}
