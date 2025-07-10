package com.empresa.excusas.model.interfaces;

import com.empresa.excusas.model.Excusa;

public interface IEncargado {
    void manejarExcusa(Excusa excusa);
    void setSiguiente(IEncargado e);
    void modoOperacion();
    boolean puedeManejar(Excusa excusa);
    void procesar(Excusa excusa);
} 