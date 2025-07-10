package com.empresa.excusas.clases;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.clasesAbstractas.Empleado;
import com.empresa.excusas.model.clasesAbstractas.TipoExcusa;
import com.empresa.excusas.model.tiposExcusas.ExcusaModerada;
import com.empresa.excusas.model.tiposExcusas.ExcusaTrivial;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para Excusa")
public class ExcusaTest {

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        empleado = new Empleado("Ana García", "ana@empresa.com", 123);
    }

    @Test
    @DisplayName("Debería crear una excusa con empleado y tipo correcto")
    void testCreacionExcusa() {
        // Arrange
        TipoExcusa tipo = new ExcusaTrivial("Me quedé dormido");

        // Act
        Excusa excusa = new Excusa(empleado, tipo);

        // Assert
        assertEquals(empleado, excusa.getEmpleado());
        assertEquals(tipo, excusa.getTipoExcusa());
        assertEquals("Me quedé dormido", excusa.getTipoExcusa().getDescripcion());
    }

    @Test
    @DisplayName("Debería manejar diferentes tipos de excusas")
    void testDiferentesTiposExcusa() {
        // Arrange
        TipoExcusa trivial = new ExcusaTrivial("Perdí el colectivo");
        TipoExcusa moderada = new ExcusaModerada("Se cortó la luz");

        // Act
        Excusa excusaTrivial = new Excusa(empleado, trivial);
        Excusa excusaModerada = new Excusa(empleado, moderada);

        // Assert
        assertTrue(excusaTrivial.getTipoExcusa() instanceof ExcusaTrivial);
        assertTrue(excusaModerada.getTipoExcusa() instanceof ExcusaModerada);
    }

    @Test
    @DisplayName("Debería generar toString con información completa")
    void testToString() {
        // Arrange
        TipoExcusa tipo = new ExcusaTrivial("Había mucho tráfico");
        Excusa excusa = new Excusa(empleado, tipo);

        // Act
        String resultado = excusa.toString();

        // Assert
        assertTrue(resultado.contains("Ana García"));
        assertTrue(resultado.contains("Había mucho tráfico"));
    }
}
