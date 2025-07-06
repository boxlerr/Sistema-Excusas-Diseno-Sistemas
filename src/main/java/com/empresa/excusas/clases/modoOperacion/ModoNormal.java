package com.empresa.excusas.clases.modoOperacion;

import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.interfaces.IEncargado;
import com.empresa.excusas.interfaces.ModoOperacion;

public class ModoNormal implements ModoOperacion {

    @Override
    public void modoOperacion() {
        System.out.println("Modo: Normal");
    }

    @Override
    public void manejarExcusa(EncargadoBase encargado, Excusa excusa) {
        if (encargado.puedeManejar(excusa)) {
            encargado.procesar(excusa);
        } else if (encargado.getSiguiente() != null) {
            encargado.getSiguiente().manejarExcusa(excusa);
        } else {
            System.out.println("Excusa rechazada: necesitamos pruebas contundentes");
        }
    }
}
