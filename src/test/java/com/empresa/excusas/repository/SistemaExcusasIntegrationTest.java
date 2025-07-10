package com.empresa.excusas.integration;

import com.empresa.excusas.model.EmpleadoExcusador;
import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.Prontuario;
import com.empresa.excusas.service.EmpleadoService;
import com.empresa.excusas.service.ExcusaService;
import com.empresa.excusas.service.ProntuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class SistemaExcusasIntegrationTest {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ExcusaService excusaService;

    @Autowired
    private ProntuarioService prontuarioService;

    @Test
    void testFlujoCompletoSistemaExcusas() {
        // 1. Crear empleado
        EmpleadoExcusador empleado = empleadoService.crearEmpleado(
            "Test Integration", "integration@test.com", 9999
        );
        
        assertThat(empleado.getId()).isNotNull();
        assertThat(empleado.getNombre()).isEqualTo("Test Integration");

        // 2. Verificar que se guardó en DB
        List<EmpleadoExcusador> empleados = empleadoService.obtenerTodosLosEmpleados();
        assertThat(empleados).isNotEmpty();
        
        // 3. Crear excusa para el empleado
        Excusa excusa = excusaService.crearExcusa(
            9999, "Me secuestraron los aliens", "INVEROSIMIL"
        );
        
        assertThat(excusa.getId()).isNotNull();
        assertThat(excusa.getDescripcion()).isEqualTo("Me secuestraron los aliens");
        assertThat(excusa.getEmpleado().getLegajo()).isEqualTo(9999);

        // 4. Verificar que la excusa se guardó
        List<Excusa> excusas = excusaService.obtenerTodasLasExcusas();
        assertThat(excusas).isNotEmpty();
        
        // 5. Verificar búsquedas específicas
        List<Excusa> excusasEmpleado = excusaService.obtenerExcusasPorLegajo(9999);
        assertThat(excusasEmpleado).hasSize(1);
        
        // 6. Verificar contadores
        long countExcusas = excusaService.contarExcusasPorEmpleado(9999);
        assertThat(countExcusas).isEqualTo(1);

        System.out.println("✅ Test de integración completado exitosamente");
        System.out.println("📊 Empleados en DB: " + empleados.size());
        System.out.println("📋 Excusas en DB: " + excusas.size());
    }

    @Test
    void testPersistenciaConDatosIniciales() {
        // Verificar que los datos del data.sql se cargaron
        List<EmpleadoExcusador> empleados = empleadoService.obtenerTodosLosEmpleados();
        List<Excusa> excusas = excusaService.obtenerTodasLasExcusas();
        
        // Debe haber al menos los datos iniciales
        assertThat(empleados.size()).isGreaterThanOrEqualTo(5); // 5 del data.sql
        assertThat(excusas.size()).isGreaterThanOrEqualTo(4);   // 4 del data.sql
        
        // Verificar empleados específicos del data.sql
        boolean juanExists = empleados.stream()
            .anyMatch(emp -> emp.getNombre().equals("Juan Pérez") && emp.getLegajo() == 1001);
        assertThat(juanExists).isTrue();
        
        System.out.println("✅ Datos iniciales cargados correctamente");
        System.out.println("👥 Total empleados: " + empleados.size());
        System.out.println("📝 Total excusas: " + excusas.size());
    }
}
