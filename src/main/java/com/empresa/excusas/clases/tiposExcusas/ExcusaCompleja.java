package com.empresa.excusas.clases.tiposExcusas;

import com.empresa.excusas.clasesAbstractas.TipoExcusa;

public class ExcusaCompleja extends TipoExcusa {
    public ExcusaCompleja(String descripcion) {
        super(descripcion);
    }

    @Override
    public boolean debeEscalar() {
        return true;
    }

    public void procesarExcusaCompleja() {
        System.out.println("🚨 Excusa compleja detectada: " + getDescripcion());
        System.out.println("📋 Requiere investigación adicional y posible escalación");
    }

    public boolean requiereInvestigacionPolicial() {
        String desc = getDescripcion().toLowerCase();
        return desc.contains("robo") || desc.contains("ladrón") || desc.contains("ladron") ||
               desc.contains("asalto") || desc.contains("secuestro");
    }

    public boolean requiereDocumentacionMedica() {
        String desc = getDescripcion().toLowerCase();
        return desc.contains("accidente") || desc.contains("lesión") || desc.contains("lesion") ||
               desc.contains("fractura") || desc.contains("hospital");
    }

    public String obtenerTipoDocumentacionRequerida() {
        if (requiereInvestigacionPolicial()) {
            return "DENUNCIA_POLICIAL";
        } else if (requiereDocumentacionMedica()) {
            return "CERTIFICADO_MEDICO";
        }
        return "DOCUMENTACION_ADICIONAL";
    }
}
