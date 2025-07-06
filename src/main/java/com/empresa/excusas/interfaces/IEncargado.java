package com.empresa.excusas.interfaces;

import com.empresa.excusas.clases.Excusa;

public interface IEncargado {
    void manejarExcusa(Excusa excusa);
    void setSiguiente(IEncargado e);
    void modoOperacion();
    boolean puedeManejar(Excusa excusa);
    void procesar(Excusa excusa);
} 