package com.empresa.excusas.clases.encargados;

import com.empresa.excusas.model.AdministradorProntuarios;
import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.clasesAbstractas.Empleado;
import com.empresa.excusas.model.encargados.CEO;
import com.empresa.excusas.model.modoOperacion.ModoNormal;
import com.empresa.excusas.model.tiposExcusas.ExcusaCompleja;
import com.empresa.excusas.model.tiposExcusas.ExcusaInverosimil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para CEO")
public class CEOTest {

    private CEO ceo;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        ceo = new CEO("Roberto Silva", "roberto@empresa.com", 103, new ModoNormal());
        empleado = new Empleado("Laura González", "laura@empresa.com", 1);
        // Limpiar prontuarios para tests aislados
        AdministradorProntuarios.getInstancia().getProntuarios().clear();
    }

    @Test
    @DisplayName("Debería poder manejar excusas inverosímiles")
    void testPuedeManejarExcusaInverosimil() {
        // Arrange
        ExcusaInverosimil excusaInverosimil = new ExcusaInverosimil("Fui abducido por aliens");
        Excusa excusa = new Excusa(empleado, excusaInverosimil);

        // Act
        boolean puedeManejar = ceo.puedeManejar(excusa);

        // Assert
        assertTrue(puedeManejar);
    }

    @Test
    @DisplayName("No debería poder manejar excusas complejas")
    void testNoPuedeManejarExcusaCompleja() {
        // Arrange
        ExcusaCompleja excusaCompleja = new ExcusaCompleja("Una paloma robó mi auto");
        Excusa excusa = new Excusa(empleado, excusaCompleja);

        // Act
        boolean puedeManejar = ceo.puedeManejar(excusa);

        // Assert
        assertFalse(puedeManejar);
    }

    @Test
    @DisplayName("Debería crear prontuario al procesar excusa inverosímil")
    void testCrearProntuarioAlProcesar() {
        // Arrange
        ExcusaInverosimil excusaInverosimil = new ExcusaInverosimil("Un unicornio bloqueó mi camino");
        Excusa excusa = new Excusa(empleado, excusaInverosimil);
        int prontuariosIniciales = AdministradorProntuarios.getInstancia().getProntuarios().size();

        // Act
        ceo.procesar(excusa);

        // Assert
        assertEquals(prontuariosIniciales + 1, AdministradorProntuarios.getInstancia().getProntuarios().size());
    }
}
