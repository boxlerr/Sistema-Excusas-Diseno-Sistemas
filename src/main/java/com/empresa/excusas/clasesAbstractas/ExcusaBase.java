package com.empresa.excusas.clasesAbstractas;

import com.empresa.excusas.interfaces.IExcusa;

public abstract class ExcusaBase implements IExcusa {
    private Empleado empleado;
    private String descripcion;

    public ExcusaBase(Empleado empleado, String descripcion) {
        this.empleado = empleado;
        this.descripcion = descripcion;
    }

    @Override
    public Empleado getEmpleado() {
        return empleado;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean debeEscalar() {
        return false;
    }

    @Override
    public String toString() {
        return "ExcusaBase{" +
                "empleado=" + empleado +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
} 