package com.empresa.excusas.clases;

import com.empresa.excusas.clases.tiposExcusas.ExcusaInverosimil;
import com.empresa.excusas.clasesAbstractas.Empleado;
import com.empresa.excusas.interfaces.ObserverProntuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para AdministradorProntuarios")
public class AdministradorProntuariosTest {

    private AdministradorProntuarios administrador;
    private Empleado empleado;
    private Excusa excusa;
    private Prontuario prontuario;

    @BeforeEach
    void setUp() {
        administrador = AdministradorProntuarios.getInstancia();
        // Limpiar prontuarios existentes para tests aislados
        administrador.getProntuarios().clear();

        empleado = new Empleado("Luis Fernández", "luis@empresa.com", 999);
        ExcusaInverosimil excusaInverosimil = new ExcusaInverosimil("Fui abducido por aliens");
        excusa = new Excusa(empleado, excusaInverosimil);
        prontuario = new Prontuario(empleado.getNombre(), empleado.getEmail(), empleado.getLegajo(), excusa);
    }

    @Test
    @DisplayName("Debería implementar patrón Singleton correctamente")
    void testSingleton() {
        // Act
        AdministradorProntuarios instancia1 = AdministradorProntuarios.getInstancia();
        AdministradorProntuarios instancia2 = AdministradorProntuarios.getInstancia();

        // Assert
        assertSame(instancia1, instancia2);
        assertSame(administrador, instancia1);
    }

    @Test
    @DisplayName("Debería agregar prontuarios correctamente")
    void testAgregarProntuario() {
        // Arrange
        int cantidadInicial = administrador.getProntuarios().size();

        // Act
        administrador.agregarProntuario(prontuario);

        // Assert
        assertEquals(cantidadInicial + 1, administrador.getProntuarios().size());
        assertTrue(administrador.getProntuarios().contains(prontuario));
    }

    // Clase auxiliar para testing
    private static class MockObserver implements ObserverProntuario {
        boolean fueNotificado = false;
        Prontuario prontuarioRecibido = null;

        @Override
        public void actualizar(Prontuario prontuario) {
            this.fueNotificado = true;
            this.prontuarioRecibido = prontuario;
        }
    }
}
