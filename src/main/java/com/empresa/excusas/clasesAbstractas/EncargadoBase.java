package com.empresa.excusas.clasesAbstractas;

import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.interfaces.Encargado;
import com.empresa.excusas.interfaces.ModoOperacion;

public abstract class EncargadoBase extends Empleado implements Encargado {
    private Encargado siguiente;
    private ModoOperacion modoOperacion;

    public EncargadoBase(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        super(nombre, email, legajo);
        this.modoOperacion = modoOperacion;
        this.siguiente = null;
    }

    public void setModoOperacion(ModoOperacion modoOperacion) {
        this.modoOperacion = modoOperacion;
    }

    public ModoOperacion getModoOperacion() {
        return this.modoOperacion;
    }

    @Override
    public void modoOperacion() {
        if (modoOperacion != null) {
            modoOperacion.modoOperacion();
        }
    }

    @Override
    public void setSiguiente(Encargado siguiente) {
        this.siguiente = siguiente;
    }

    public Encargado getSiguiente() {
        return this.siguiente;
    }

    @Override
    public void manejarExcusa(Excusa excusa) {
        if (modoOperacion != null) {
            modoOperacion.manejarExcusa(this, excusa);
        } else {
            System.out.println("No hay modo de operación asignado.");
        }
    }

    public abstract boolean puedeManejar(Excusa excusa);
    public abstract void procesar(Excusa excusa);
}
