package com.empresa.excusas.model.tiposExcusas;

import com.empresa.excusas.model.clasesAbstractas.TipoExcusa;

public class ExcusaTrivial extends TipoExcusa {
    public ExcusaTrivial(String descripcion) {
        super(descripcion);
    }

    @Override
    public boolean debeEscalar() {
        return false;
    }

    public void procesarExcusaTrivial() {
        System.out.println("✅ Excusa trivial procesada automáticamente: " + getDescripcion());
        System.out.println("📝 No requiere escalación - aprobada por defecto");
    }

    public String obtenerMensajeAprobacion() {
        return "Su excusa trivial ha sido aprobada automáticamente.";
    }
}
