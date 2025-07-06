package com.empresa.excusas.clases.tiposExcusas;

import com.empresa.excusas.clasesAbstractas.TipoExcusa;

public class ExcusaModerada extends TipoExcusa {
    public ExcusaModerada(String descripcion) {
        super(descripcion);
    }

    @Override
    public boolean debeEscalar() {
        return false;
    }

    public void procesarExcusaModerada() {
        System.out.println("⚠️ Excusa moderada requiere revisión: " + getDescripcion());
        System.out.println("🔍 Se enviará consulta para verificar la situación");
    }

    public boolean requiereConsultaExterna() {
        String desc = getDescripcion().toLowerCase();
        return desc.contains("luz") || desc.contains("energía") || desc.contains("energia") ||
               desc.contains("familiar") || desc.contains("familia");
    }

    public String obtenerTipoConsulta() {
        String desc = getDescripcion().toLowerCase();
        if (desc.contains("luz") || desc.contains("energía") || desc.contains("energia")) {
            return "CONSULTA_EDESUR";
        } else if (desc.contains("familiar") || desc.contains("familia")) {
            return "CONSULTA_FAMILIAR";
        }
        return "CONSULTA_GENERAL";
    }
}
