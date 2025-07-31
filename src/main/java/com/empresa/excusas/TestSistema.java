package com.empresa.excusas;

import com.empresa.excusas.model.clasesAbstractas.Empleado;
import com.empresa.excusas.model.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.model.EmpleadoExcusador;
import com.empresa.excusas.model.EncargadoFactory;
import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.modoOperacion.ModoNormal;
import com.empresa.excusas.model.modoOperacion.ModoProductivo;
import com.empresa.excusas.model.interfaces.IEncargado;
import com.empresa.excusas.model.interfaces.ModoOperacion;
import com.empresa.excusas.model.modoOperacion.*;
import com.empresa.excusas.model.tiposExcusas.*;

public class TestSistema {
    public static void main(String[] args) {
        System.out.println("🧪 Iniciando pruebas del sistema de excusas con auditoría...");
        
        // Crear empleados
        Empleado empleado1 = new Empleado("Juan Pérez", "juan@empresa.com", 1001);
        Empleado empleado2 = new Empleado("María García", "maria@empresa.com", 1002);
        
        // Crear modos de operación
        ModoOperacion modoNormal = new ModoNormal();
        ModoOperacion modoProductivo = new ModoProductivo();
        
        // Crear factory para encargados con auditoría
        EncargadoFactory factory = new EncargadoFactory();
        
        // Crear cadena de encargados CON AUDITORÍA
        IEncargado recepcionista = factory.crearRecepcionistaConAuditoria("Ana López", "ana@empresa.com", 2001, modoNormal);
        IEncargado supervisor = factory.crearSupervisorConAuditoria("Carlos Ruiz", "carlos@empresa.com", 2002, modoProductivo);
        IEncargado gerenteRRHH = factory.crearGerenteRRHHConAuditoria("Laura Silva", "laura@empresa.com", 2003, modoNormal);
        IEncargado ceo = factory.crearCEOConAuditoria("Roberto Martínez", "roberto@empresa.com", 2004, modoNormal);
        IEncargado encargadoDefecto = factory.crearEncargadoPorDefectoConAuditoria("Sistema", "sistema@empresa.com", 9999, modoNormal);
        
        // Configurar cadena de responsabilidad
        recepcionista.setSiguiente(supervisor);
        supervisor.setSiguiente(gerenteRRHH);
        gerenteRRHH.setSiguiente(ceo);
        ceo.setSiguiente(encargadoDefecto);
        
        // Crear excusas de diferentes tipos
        Excusa excusaTrivial = new Excusa((EmpleadoExcusador)empleado1, "ME_QUEDE_DORMIDO", "TRIVIAL");
        Excusa excusaModerada = new Excusa((EmpleadoExcusador)empleado1, "CUIDADO_FAMILIAR", "MODERADA");
        Excusa excusaCompleja = new Excusa((EmpleadoExcusador)empleado2, "PROBLEMA_TRANSPORTE", "COMPLEJA");
        Excusa excusaInverosimil = new Excusa((EmpleadoExcusador)empleado2, "INVASION_EXTRATERRESTRE", "INVEROSIMIL");
        
        System.out.println("\n=== PRUEBA 1: Excusa Trivial ===");
        recepcionista.manejarExcusa(excusaTrivial);
        
        System.out.println("\n=== PRUEBA 2: Excusa Moderada ===");
        recepcionista.manejarExcusa(excusaModerada);
        
        System.out.println("\n=== PRUEBA 3: Excusa Compleja ===");
        recepcionista.manejarExcusa(excusaCompleja);
        
        System.out.println("\n=== PRUEBA 4: Excusa Inverosímil ===");
        recepcionista.manejarExcusa(excusaInverosimil);
        
        System.out.println("\n✅ Todas las pruebas completadas exitosamente!");
        System.out.println("📋 Se han registrado todos los logs de auditoría.");
    }
} 