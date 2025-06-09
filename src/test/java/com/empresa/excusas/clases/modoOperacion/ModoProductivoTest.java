package com.empresa.excusas.clases.modoOperacion;

import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clases.encargados.Recepcionista;
import com.empresa.excusas.clases.tiposExcusas.ExcusaTrivial;
import com.empresa.excusas.clasesAbstractas.Empleado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para ModoProductivo")
public class ModoProductivoTest {

    private ModoProductivo modoProductivo;
    private Empleado empleado;
    private Excusa excusa;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() {
        modoProductivo = new ModoProductivo();
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
    @DisplayName("Debería mostrar modo PRODUCTIVO")
    void testModoOperacion() {
        // Act
        modoProductivo.modoOperacion();

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("PRODUCTIVO"));
    }

    @Test
    @DisplayName("Debería enviar email al CTO cuando procesa excusa")
    void testEnviarEmailAlCTO() {
        // Arrange
        Recepcionista recepcionista = new Recepcionista("Test", "test@test.com", 1, modoProductivo);

        // Act
        modoProductivo.manejarExcusa(recepcionista, excusa);

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("cto@empresa.com"));
        assertTrue(output.contains("Excusa recibida"));
    }
}
