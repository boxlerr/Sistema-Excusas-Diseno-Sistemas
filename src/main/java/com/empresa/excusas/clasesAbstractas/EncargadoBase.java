package com.empresa.excusas.clasesAbstractas;

import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.interfaces.IEncargado;
import com.empresa.excusas.interfaces.ModoOperacion;

public abstract class EncargadoBase implements IEncargado {
    private String nombre;
    private String email;
    private int legajo;
    private IEncargado siguiente;
    private ModoOperacion modoOperacion;

    public EncargadoBase(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        this.nombre = nombre;
        this.email = email;
        this.legajo = legajo;
        this.modoOperacion = modoOperacion;
        this.siguiente = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
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
    public void setSiguiente(IEncargado siguiente) {
        this.siguiente = siguiente;
    }

    public IEncargado getSiguiente() {
        return this.siguiente;
    }

    @Override
    public void manejarExcusa(Excusa excusa) {
        if (puedeManejar(excusa)) {
            procesar(excusa);
        } else if (siguiente != null) {
            siguiente.manejarExcusa(excusa);
        } else {
            System.out.println("❌ No se pudo procesar la excusa. No hay encargado disponible.");
        }
    }

    @Override
    public String toString() {
        return "EncargadoBase{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", legajo=" + legajo +
                '}';
    }

    public abstract boolean puedeManejar(Excusa excusa);
    public abstract void procesar(Excusa excusa);
}
