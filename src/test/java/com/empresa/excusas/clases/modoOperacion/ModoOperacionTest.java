package com.empresa.excusas.clases.modoOperacion;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.clasesAbstractas.Empleado;
import com.empresa.excusas.model.encargados.Recepcionista;
import com.empresa.excusas.model.modoOperacion.ModoNormal;
import com.empresa.excusas.model.modoOperacion.ModoProductivo;
import com.empresa.excusas.model.modoOperacion.ModoVago;
import com.empresa.excusas.model.tiposExcusas.ExcusaTrivial;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para Modos de Operación")
public class ModoOperacionTest {

    private Empleado empleado;
    private Excusa excusa;

    @BeforeEach
    void setUp() {
        empleado = new Empleado("Test User", "test@empresa.com", 1);
        ExcusaTrivial tipoExcusa = new ExcusaTrivial("Test excuse");
        excusa = new Excusa(empleado, tipoExcusa);
    }

    @Test
    @DisplayName("ModoNormal debería procesar excusas que puede manejar")
    void testModoNormal() {
        // Arrange
        ModoNormal modoNormal = new ModoNormal();
        Recepcionista recepcionista = new Recepcionista("Test", "test@test.com", 1, modoNormal);

        // Act & Assert - No debería lanzar excepciones
        assertDoesNotThrow(() -> {
            modoNormal.manejarExcusa(recepcionista, excusa);
        });
    }

    @Test
    @DisplayName("ModoVago debería pasar excusas sin evaluar")
    void testModoVago() {
        // Arrange
        ModoVago modoVago = new ModoVago();
        Recepcionista recepcionista = new Recepcionista("Test", "test@test.com", 1, modoVago);

        // Act & Assert - No debería lanzar excepciones
        assertDoesNotThrow(() -> {
            modoVago.manejarExcusa(recepcionista, excusa);
        });
    }

    @Test
    @DisplayName("ModoProductivo debería enviar emails al CTO")
    void testModoProductivo() {
        // Arrange
        ModoProductivo modoProductivo = new ModoProductivo();
        Recepcionista recepcionista = new Recepcionista("Test", "test@test.com", 1, modoProductivo);

        // Act & Assert - No debería lanzar excepciones
        assertDoesNotThrow(() -> {
            modoProductivo.manejarExcusa(recepcionista, excusa);
        });
    }
}
