package com.empresa.excusas;

import com.empresa.excusas.clases.*;
import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.clasesAbstractas.Empleado;
import com.empresa.excusas.model.encargados.*;
import com.empresa.excusas.model.interfaces.IEncargado;
import com.empresa.excusas.model.interfaces.ModoOperacion;
import com.empresa.excusas.model.modoOperacion.*;
import com.empresa.excusas.model.tiposExcusas.*;

public class TestSistema {
    public static void main(String[] args) {
        System.out.println("🧪 Iniciando pruebas del sistema de excusas...");
        
        // Crear empleados
        Empleado empleado1 = new Empleado("Juan Pérez", "juan@empresa.com", 1001);
        Empleado empleado2 = new Empleado("María García", "maria@empresa.com", 1002);
        
        // Crear modos de operación
        ModoOperacion modoNormal = new ModoNormal();
        ModoOperacion modoProductivo = new ModoProductivo();
        
        // Crear cadena de encargados
        IEncargado recepcionista = new Recepcionista("Ana López", "ana@empresa.com", 2001, modoNormal);
        IEncargado supervisor = new SupervisorArea("Carlos Ruiz", "carlos@empresa.com", 2002, modoProductivo);
        IEncargado gerenteRRHH = new GerenteRRHH("Laura Silva", "laura@empresa.com", 2003, modoNormal);
        IEncargado ceo = new CEO("Roberto Martínez", "roberto@empresa.com", 2004, modoNormal);
        IEncargado encargadoDefecto = new EncargadoPorDefecto("Sistema", "sistema@empresa.com", 9999, modoNormal);
        
        // Configurar cadena de responsabilidad
        recepcionista.setSiguiente(supervisor);
        supervisor.setSiguiente(gerenteRRHH);
        gerenteRRHH.setSiguiente(ceo);
        ceo.setSiguiente(encargadoDefecto);
        
        // Crear excusas de diferentes tipos
        Excusa excusaTrivial = new Excusa(empleado1, "Me quedé dormido", new ExcusaTrivial("Me quedé dormido"));
        Excusa excusaModerada = new Excusa(empleado1, "Corte de luz en mi casa", new ExcusaModerada("Corte de luz en mi casa"));
        Excusa excusaCompleja = new Excusa(empleado2, "Me robaron el auto", new ExcusaCompleja("Me robaron el auto"));
        Excusa excusaInverosimil = new Excusa(empleado2, "Me abdujo un alien", new ExcusaInverosimil("Me abdujo un alien"));
        
        System.out.println("\n=== PRUEBA 1: Excusa Trivial ===");
        recepcionista.manejarExcusa(excusaTrivial);
        
        System.out.println("\n=== PRUEBA 2: Excusa Moderada ===");
        recepcionista.manejarExcusa(excusaModerada);
        
        System.out.println("\n=== PRUEBA 3: Excusa Compleja ===");
        recepcionista.manejarExcusa(excusaCompleja);
        
        System.out.println("\n=== PRUEBA 4: Excusa Inverosímil ===");
        recepcionista.manejarExcusa(excusaInverosimil);
        
        System.out.println("\n✅ Todas las pruebas completadas exitosamente!");
    }
} 