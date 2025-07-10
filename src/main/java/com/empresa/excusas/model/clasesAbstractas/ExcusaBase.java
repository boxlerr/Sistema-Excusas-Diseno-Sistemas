package com.empresa.excusas.model.clasesAbstractas;

import com.empresa.excusas.model.interfaces.IExcusa;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class ExcusaBase implements IExcusa {
    
    @Column(nullable = false, length = 1000)
    private String descripcion;

    public ExcusaBase() {}

    public ExcusaBase(Empleado empleado, String descripcion) {
        this.descripcion = descripcion;
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
                "descripcion='" + descripcion + '\'' +
                '}';
    }
}
