package com.empresa.excusas;

import com.empresa.excusas.model.clasesAbstractas.Empleado;
import com.empresa.excusas.model.EmpleadoExcusador;
import com.empresa.excusas.model.EncargadoFactory;
import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.modoOperacion.ModoNormal;
import com.empresa.excusas.model.interfaces.IEncargado;
import com.empresa.excusas.model.interfaces.ModoOperacion;

public class TestAuditoria {
    public static void main(String[] args) {
        System.out.println("🔍 DEMOSTRACIÓN DEL SISTEMA DE AUDITORÍA");
        System.out.println("=========================================");
        
        // Crear empleados
        Empleado empleado1 = new Empleado("Juan Pérez", "juan@empresa.com", 1001);
        Empleado empleado2 = new Empleado("María García", "maria@empresa.com", 1002);
        
        // Crear modo de operación
        ModoOperacion modoNormal = new ModoNormal();
        
        // Crear factory para encargados con auditoría
        EncargadoFactory factory = new EncargadoFactory();
        
        // Crear cadena de encargados CON AUDITORÍA
        IEncargado recepcionista = factory.crearRecepcionistaConAuditoria("Ana López", "ana@empresa.com", 2001, modoNormal);
        IEncargado supervisor = factory.crearSupervisorConAuditoria("Carlos Ruiz", "carlos@empresa.com", 2002, modoNormal);
        IEncargado gerenteRRHH = factory.crearGerenteRRHHConAuditoria("Laura Silva", "laura@empresa.com", 2003, modoNormal);
        IEncargado ceo = factory.crearCEOConAuditoria("Roberto Martínez", "roberto@empresa.com", 2004, modoNormal);
        IEncargado encargadoDefecto = factory.crearEncargadoPorDefectoConAuditoria("Sistema", "sistema@empresa.com", 9999, modoNormal);
        
        // Configurar cadena de responsabilidad
        recepcionista.setSiguiente(supervisor);
        supervisor.setSiguiente(gerenteRRHH);
        gerenteRRHH.setSiguiente(ceo);
        ceo.setSiguiente(encargadoDefecto);
        
        System.out.println("\n📋 Ejemplos de logs de auditoría esperados:");
        System.out.println("----------------------------------------");
        
        // Crear y procesar excusas para demostrar la auditoría
        Excusa excusa1 = new Excusa((EmpleadoExcusador)empleado1, "ME_QUEDE_DORMIDO", "TRIVIAL");
        System.out.println("\n🔄 Procesando excusa: " + excusa1.getDescripcion());
        recepcionista.manejarExcusa(excusa1);
        
        Excusa excusa2 = new Excusa((EmpleadoExcusador)empleado1, "CUIDADO_FAMILIAR", "MODERADA");
        System.out.println("\n🔄 Procesando excusa: " + excusa2.getDescripcion());
        recepcionista.manejarExcusa(excusa2);
        
        Excusa excusa3 = new Excusa((EmpleadoExcusador)empleado2, "PROBLEMA_TRANSPORTE", "COMPLEJA");
        System.out.println("\n🔄 Procesando excusa: " + excusa3.getDescripcion());
        recepcionista.manejarExcusa(excusa3);
        
        Excusa excusa4 = new Excusa((EmpleadoExcusador)empleado2, "INVASION_EXTRATERRESTRE", "INVEROSIMIL");
        System.out.println("\n🔄 Procesando excusa: " + excusa4.getDescripcion());
        recepcionista.manejarExcusa(excusa4);
        
        System.out.println("\n✅ Demostración completada!");
        System.out.println("📊 Se han registrado todos los logs de auditoría en la consola.");
        System.out.println("\n💡 Observaciones:");
        System.out.println("- Cada excusa procesada genera un log de auditoría");
        System.out.println("- El log incluye: nombre del encargado, tipo de excusa y resultado");
        System.out.println("- Los resultados pueden ser: ACEPTADA, RECHAZADA o DERIVADA");
        System.out.println("- El sistema de auditoría se agregó sin modificar las clases existentes");
    }
} 