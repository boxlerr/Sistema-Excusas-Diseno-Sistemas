package com.empresa.excusas.clases.encargados;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.clasesAbstractas.Empleado;
import com.empresa.excusas.model.encargados.GerenteRRHH;
import com.empresa.excusas.model.modoOperacion.ModoNormal;
import com.empresa.excusas.model.modoOperacion.ModoVago;
import com.empresa.excusas.model.tiposExcusas.ExcusaCompleja;
import com.empresa.excusas.model.tiposExcusas.ExcusaModerada;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para GerenteRRHH")
public class GerenteRRHHTest {

    private GerenteRRHH gerente;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        gerente = new GerenteRRHH("María Rodríguez", "maria@empresa.com", 102, new ModoNormal());
        empleado = new Empleado("Pedro Martínez", "pedro@empresa.com", 1);
    }

    @Test
    @DisplayName("Debería poder manejar excusas complejas")
    void testPuedeManejarExcusaCompleja() {
        // Arrange
        ExcusaCompleja excusaCompleja = new ExcusaCompleja("Una paloma robó mi bicicleta");
        Excusa excusa = new Excusa(empleado, excusaCompleja);

        // Act
        boolean puedeManejar = gerente.puedeManejar(excusa);

        // Assert
        assertTrue(puedeManejar);
    }

    @Test
    @DisplayName("No debería poder manejar excusas moderadas")
    void testNoPuedeManejarExcusaModerada() {
        // Arrange
        ExcusaModerada excusaModerada = new ExcusaModerada("Se cortó la luz");
        Excusa excusa = new Excusa(empleado, excusaModerada);

        // Act
        boolean puedeManejar = gerente.puedeManejar(excusa);

        // Assert
        assertFalse(puedeManejar);
    }
}
