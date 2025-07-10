package com.empresa.excusas.model.clasesAbstractas;

import com.empresa.excusas.model.interfaces.ITipoExcusa;

public abstract class TipoExcusa implements ITipoExcusa {
    private String descripcion;

    public TipoExcusa(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoExcusa{" +
                "descripcion='" + descripcion + '\'' +
                '}';
    }

    @Override
    public boolean debeEscalar() {
        return false;
    }
}
