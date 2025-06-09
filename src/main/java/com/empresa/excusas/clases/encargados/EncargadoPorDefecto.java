package com.empresa.excusas.clases.encargados;

import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.interfaces.ModoOperacion;

public class EncargadoPorDefecto extends EncargadoBase {

    public EncargadoPorDefecto(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        super(nombre, email, legajo, modoOperacion);
    }

    @Override
    public boolean puedeManejar(Excusa excusa) {
        return true; // Siempre puede "manejar" para dar la respuesta por defecto
    }

    @Override
    public void procesar(Excusa excusa) {
        System.out.println("❌ " + this.getNombre() + " (Encargado por Defecto): Excusa rechazada: necesitamos pruebas contundentes");
    }
}
