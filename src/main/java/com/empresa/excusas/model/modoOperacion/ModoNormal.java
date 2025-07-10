package com.empresa.excusas.model.modoOperacion;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.model.interfaces.IEncargado;
import com.empresa.excusas.model.interfaces.ModoOperacion;

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
