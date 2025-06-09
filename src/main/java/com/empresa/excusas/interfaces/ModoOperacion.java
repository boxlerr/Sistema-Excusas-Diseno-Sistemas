package com.empresa.excusas.interfaces;

import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clasesAbstractas.EncargadoBase;

public interface ModoOperacion {
    void modoOperacion();
    void manejarExcusa(EncargadoBase encargado, Excusa excusa);
}
