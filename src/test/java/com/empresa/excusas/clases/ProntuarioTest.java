package com.empresa.excusas.clases;

import com.empresa.excusas.clases.tiposExcusas.ExcusaInverosimil;
import com.empresa.excusas.clasesAbstractas.Empleado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para Prontuario")
public class ProntuarioTest {

    private Empleado empleado;
    private Excusa excusa;
    private Prontuario prontuario;

    @BeforeEach
    void setUp() {
        empleado = new Empleado("Carlos Mendoza", "carlos@empresa.com", 555);
        ExcusaInverosimil tipoExcusa = new ExcusaInverosimil("Me convertí en zombie temporalmente");
        excusa = new Excusa(empleado, tipoExcusa);
        prontuario = new Prontuario(empleado.getNombre(), empleado.getEmail(), empleado.getLegajo(), excusa);
    }

    @Test
    @DisplayName("Debería crear un prontuario con todos los datos correctos")
    void testCreacionProntuario() {
        // Assert
        assertEquals("Carlos Mendoza", prontuario.getNombreEmpleado());
        assertEquals("carlos@empresa.com", prontuario.getEmailEmpleado());
        assertEquals(555, prontuario.getLegajoEmpleado());
        assertEquals(excusa, prontuario.getExcusa());
    }

    @Test
    @DisplayName("Debería generar toString con información completa")
    void testToString() {
        // Act
        String resultado = prontuario.toString();

        // Assert
        assertTrue(resultado.contains("Carlos Mendoza"));
        assertTrue(resultado.contains("carlos@empresa.com"));
        assertTrue(resultado.contains("555"));
        assertTrue(resultado.contains("zombie"));
    }

    @Test
    @DisplayName("Debería mantener referencia a la excusa original")
    void testReferenciaExcusa() {
        // Assert
        assertSame(excusa, prontuario.getExcusa());
        assertEquals("Me convertí en zombie temporalmente", prontuario.getExcusa().getTipoExcusa().getDescripcion());
    }
}
