package com.empresa.excusas.clases.tiposExcusas;

import com.empresa.excusas.clases.tiposExcusas.ExcusaModerada;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para ExcusaModerada")
public class ExcusaModeradaTest {

    @Test
    @DisplayName("Debería crear una excusa moderada correctamente")
    public void testCreacionExcusaModerada() {
        // Arrange
        String descripcion = "Se cortó la luz en todo el barrio";

        // Act
        ExcusaModerada excusa = new ExcusaModerada(descripcion);

        // Assert
        assertEquals(descripcion, excusa.getDescripcion());
        assertNotNull(excusa);
    }
}
