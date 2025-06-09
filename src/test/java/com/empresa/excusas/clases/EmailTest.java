package com.empresa.excusas.clases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para Email")
public class EmailTest {

    private Email email;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() {
        email = new Email();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Debería enviar email con todos los parámetros correctos")
    void testEnviarEmail() {
        // Arrange
        String destino = "empleado@empresa.com";
        String origen = "recepcionista@empresa.com";
        String asunto = "motivo demora";
        String cuerpo = "la licencia fue aceptada";

        // Act
        email.enviarEmail(destino, origen, asunto, cuerpo);

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("=== EMAIL ENVIADO ==="));
        assertTrue(output.contains("De: " + origen));
        assertTrue(output.contains("Para: " + destino));
        assertTrue(output.contains("Asunto: " + asunto));
        assertTrue(output.contains("Cuerpo: " + cuerpo));
    }

    @Test
    @DisplayName("Debería manejar emails con caracteres especiales")
    void testEnviarEmailConCaracteresEspeciales() {
        // Arrange
        String destino = "test@empresa.com";
        String origen = "sistema@empresa.com";
        String asunto = "Consulta: ¿Todo está bien?";
        String cuerpo = "Mensaje con acentos: José, María & Ñoño";

        // Act
        email.enviarEmail(destino, origen, asunto, cuerpo);

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("José"));
        assertTrue(output.contains("María"));
        assertTrue(output.contains("Ñoño"));
        assertTrue(output.contains("&"));
    }
}
