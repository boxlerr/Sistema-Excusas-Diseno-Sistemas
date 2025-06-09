package com.empresa.excusas.interfaces;

import com.empresa.excusas.clases.Excusa;

public interface Encargado {
    void manejarExcusa(Excusa excusa);
    void setSiguiente(Encargado e);
    void modoOperacion();
}
