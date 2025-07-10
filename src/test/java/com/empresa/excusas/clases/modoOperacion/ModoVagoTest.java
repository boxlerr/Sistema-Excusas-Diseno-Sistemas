package com.empresa.excusas.clases.modoOperacion;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.clasesAbstractas.Empleado;
import com.empresa.excusas.model.encargados.Recepcionista;
import com.empresa.excusas.model.modoOperacion.ModoVago;
import com.empresa.excusas.model.tiposExcusas.ExcusaTrivial;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para ModoVago")
public class ModoVagoTest {

    private ModoVago modoVago;
    private Empleado empleado;
    private Excusa excusa;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() {
        modoVago = new ModoVago();
        empleado = new Empleado("Test User", "test@empresa.com", 1);
        ExcusaTrivial tipoExcusa = new ExcusaTrivial("Test excuse");
        excusa = new Excusa(empleado, tipoExcusa);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Debería mostrar modo Vago")
    void testModoOperacion() {
        // Act
        modoVago.modoOperacion();

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Vago"));
    }

    @Test
    @DisplayName("Debería pasar excusa sin evaluar")
    void testPasarExcusaSinEvaluar() {
        // Arrange
        Recepcionista recepcionista = new Recepcionista("Test", "test@test.com", 1, modoVago);

        // Act
        modoVago.manejarExcusa(recepcionista, excusa);

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("modo vago"));
        assertTrue(output.contains("pasa la excusa sin evaluar"));
    }
}
