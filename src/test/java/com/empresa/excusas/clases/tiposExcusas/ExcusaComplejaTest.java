package com.empresa.excusas.clases.tiposExcusas;

import org.junit.jupiter.api.Test;

import com.empresa.excusas.model.tiposExcusas.ExcusaCompleja;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para ExcusaCompleja")
public class ExcusaComplejaTest {

    @Test
    @DisplayName("Debería crear una excusa compleja correctamente")
    public void testCreacionExcusaCompleja() {
        // Arrange
        String descripcion = "Una paloma robó mi bicicleta";

        // Act
        ExcusaCompleja excusa = new ExcusaCompleja(descripcion);

        // Assert
        assertEquals(descripcion, excusa.getDescripcion());
        assertNotNull(excusa);
        assertTrue(excusa.toString().contains(descripcion));
    }

    @Test
    @DisplayName("Debería manejar descripciones largas")
    public void testExcusaComplejaConDescripcionLarga() {
        // Arrange
        String descripcion = "Hubo una inundación en mi barrio causada por la rotura de una cañería principal que afectó toda la zona y no pude salir de mi casa";

        // Act
        ExcusaCompleja excusa = new ExcusaCompleja(descripcion);

        // Assert
        assertEquals(descripcion, excusa.getDescripcion());
        assertTrue(excusa.toString().contains("inundación"));
    }
}
