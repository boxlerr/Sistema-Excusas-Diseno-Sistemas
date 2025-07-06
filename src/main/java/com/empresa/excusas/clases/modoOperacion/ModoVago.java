package com.empresa.excusas.clases.modoOperacion;

import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.interfaces.IEncargado;
import com.empresa.excusas.interfaces.ModoOperacion;

public class ModoVago implements ModoOperacion {

    @Override
    public void modoOperacion() {
        System.out.println("Modo: Vago - No evalúa la excusa, la pasa directamente al siguiente");
    }

    @Override
    public void manejarExcusa(EncargadoBase encargado, Excusa excusa) {
        // En modo vago, NO evalúa si puede manejar la excusa, simplemente la pasa al siguiente
        System.out.println("😴 " + encargado.getNombre() + " está en modo vago y pasa la excusa sin evaluar");

        if (encargado.getSiguiente() != null) {
            encargado.getSiguiente().manejarExcusa(excusa);
        } else {
            System.out.println("Excusa rechazada: necesitamos pruebas contundentes");
        }
    }
}
