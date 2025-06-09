package com.empresa.excusas.clases.tiposExcusas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para ExcusaTrivial")
public class ExcusaTrivialTest {

    @Test
    @DisplayName("Debería crear una excusa trivial con descripción correcta")
    public void testCreacionExcusaTrivial() {
        // Arrange
        String descripcion = "Me quedé dormido";

        // Act
        ExcusaTrivial excusa = new ExcusaTrivial(descripcion);

        // Assert
        assertEquals(descripcion, excusa.getDescripcion());
        assertNotNull(excusa);
        assertTrue(excusa.toString().contains(descripcion));
    }

    @Test
    @DisplayName("Debería manejar descripciones vacías")
    public void testExcusaTrivialConDescripcionVacia() {
        // Arrange
        String descripcion = "";

        // Act
        ExcusaTrivial excusa = new ExcusaTrivial(descripcion);

        // Assert
        assertEquals("", excusa.getDescripcion());
        assertNotNull(excusa);
    }

    @Test
    @DisplayName("Debería manejar descripciones con caracteres especiales")
    public void testExcusaTrivialConCaracteresEspeciales() {
        // Arrange
        String descripcion = "Perdí el colectivo por el tráfico & lluvia!";

        // Act
        ExcusaTrivial excusa = new ExcusaTrivial(descripcion);

        // Assert
        assertEquals(descripcion, excusa.getDescripcion());
        assertTrue(excusa.toString().contains("&"));
        assertTrue(excusa.toString().contains("!"));
    }
}
