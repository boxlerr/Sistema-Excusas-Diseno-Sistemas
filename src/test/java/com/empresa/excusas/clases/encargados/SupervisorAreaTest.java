package com.empresa.excusas.clases.encargados;

import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clases.modoOperacion.ModoProductivo;
import com.empresa.excusas.clases.tiposExcusas.ExcusaModerada;
import com.empresa.excusas.clases.tiposExcusas.ExcusaTrivial;
import com.empresa.excusas.clasesAbstractas.Empleado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para SupervisorArea")
public class SupervisorAreaTest {

    private SupervisorArea supervisor;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        supervisor = new SupervisorArea("Carlos López", "carlos@empresa.com", 101, new ModoProductivo());
        empleado = new Empleado("Ana García", "ana@empresa.com", 1);
    }

    @Test
    @DisplayName("Debería poder manejar excusas moderadas")
    void testPuedeManejarExcusaModerada() {
        // Arrange
        ExcusaModerada excusaModerada = new ExcusaModerada("Se cortó la luz en todo el barrio");
        Excusa excusa = new Excusa(empleado, excusaModerada);

        // Act
        boolean puedeManejar = supervisor.puedeManejar(excusa);

        // Assert
        assertTrue(puedeManejar);
    }

    @Test
    @DisplayName("No debería poder manejar excusas triviales")
    void testNoPuedeManejarExcusaTrivial() {
        // Arrange
        ExcusaTrivial excusaTrivial = new ExcusaTrivial("Me quedé dormido");
        Excusa excusa = new Excusa(empleado, excusaTrivial);

        // Act
        boolean puedeManejar = supervisor.puedeManejar(excusa);

        // Assert
        assertFalse(puedeManejar);
    }
}
