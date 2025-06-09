package com.empresa.excusas.clasesAbstractas;

public abstract class TipoExcusa {
    private String descripcion;

    public TipoExcusa(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoExcusa{" +
                "descripcion='" + descripcion + '\'' +
                '}';
    }

    public boolean debeEscalar() {
        return false;
    }
}
