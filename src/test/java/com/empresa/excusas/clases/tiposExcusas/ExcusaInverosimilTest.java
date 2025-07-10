package com.empresa.excusas.clases.tiposExcusas;

import org.junit.jupiter.api.Test;

import com.empresa.excusas.model.tiposExcusas.ExcusaInverosimil;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para ExcusaInverosimil")
public class ExcusaInverosimilTest {

    @Test
    @DisplayName("Debería crear una excusa inverosímil correctamente")
    public void testCreacionExcusaInverosimil() {
        // Arrange
        String descripcion = "Fui abducido por aliens";

        // Act
        ExcusaInverosimil excusa = new ExcusaInverosimil(descripcion);

        // Assert
        assertEquals(descripcion, excusa.getDescripcion());
        assertNotNull(excusa);
        assertTrue(excusa.toString().contains(descripcion));
    }

    @Test
    @DisplayName("Debería manejar excusas fantásticas")
    public void testExcusaFantastica() {
        // Arrange
        String descripcion = "Un unicornio bloqueó mi camino al trabajo";

        // Act
        ExcusaInverosimil excusa = new ExcusaInverosimil(descripcion);

        // Assert
        assertEquals(descripcion, excusa.getDescripcion());
        assertTrue(excusa.toString().contains("unicornio"));
    }
}
