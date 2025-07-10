package com.empresa.excusas.model.tiposExcusas;

import com.empresa.excusas.model.clasesAbstractas.TipoExcusa;

public class ExcusaInverosimil extends TipoExcusa {
    public ExcusaInverosimil(String descripcion) {
        super(descripcion);
    }

    @Override
    public boolean debeEscalar() {
        return true;
    }

    public void procesarExcusaInverosimil() {
        System.out.println("🤖 Excusa inverosímil detectada: " + getDescripcion());
        System.out.println("🚫 Rechazada automáticamente por ser imposible");
    }

    public boolean esExcusaFantastica() {
        String desc = getDescripcion().toLowerCase();
        return desc.contains("alien") || desc.contains("fantasma") || desc.contains("dragón") ||
               desc.contains("dragon") || desc.contains("mago") || desc.contains("hechizo");
    }

    public boolean esExcusaCientificamenteImposible() {
        String desc = getDescripcion().toLowerCase();
        return desc.contains("viaje en el tiempo") || desc.contains("teletransporte") ||
               desc.contains("invisibilidad") || desc.contains("superpoder");
    }

    public String obtenerRazonRechazo() {
        if (esExcusaFantastica()) {
            return "EXCUSA_FANTASTICA_IMPOSIBLE";
        } else if (esExcusaCientificamenteImposible()) {
            return "EXCUSA_CIENTIFICAMENTE_IMPOSIBLE";
        }
        return "EXCUSA_INVEROSIMIL_GENERAL";
    }

    public void registrarRechazo() {
        System.out.println("📝 Registrando rechazo de excusa inverosímil en el sistema");
        System.out.println("⚠️ Se notificará al empleado sobre la imposibilidad de la excusa");
    }
}
