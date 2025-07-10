package com.empresa.excusas.model.interfaces;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.clasesAbstractas.EncargadoBase;

public interface ModoOperacion {
    void modoOperacion();
    void manejarExcusa(EncargadoBase encargado, Excusa excusa);
}
