package com.empresa.excusas.clases.encargados;

import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clases.modoOperacion.ModoNormal;
import com.empresa.excusas.clases.tiposExcusas.ExcusaTrivial;
import com.empresa.excusas.clases.tiposExcusas.ExcusaModerada;
import com.empresa.excusas.clasesAbstractas.Empleado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para Recepcionista")
public class RecepcionistaTest {

    private Recepcionista recepcionista;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        recepcionista = new Recepcionista("María López", "maria@empresa.com", 100, new ModoNormal());
        empleado = new Empleado("Carlos Ruiz", "carlos@empresa.com", 1);
    }

    @Test
    @DisplayName("Debería poder manejar excusas triviales")
    void testPuedeManejarExcusaTrivial() {
        // Arrange
        ExcusaTrivial excusaTrivial = new ExcusaTrivial("Me quedé dormido");
        Excusa excusa = new Excusa(empleado, excusaTrivial);

        // Act
        boolean puedeManejar = recepcionista.puedeManejar(excusa);

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
        boolean puedeManejar = recepcionista.puedeManejar(excusa);

        // Assert
        assertFalse(puedeManejar);
    }
}
