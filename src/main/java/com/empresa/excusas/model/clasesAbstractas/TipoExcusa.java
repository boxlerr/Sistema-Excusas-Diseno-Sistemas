package com.empresa.excusas.model.clasesAbstractas;

public enum TipoExcusa {
    TRIVIAL {
        @Override
        public boolean debeEscalar(String descripcion) { return false; }
        @Override
        public void procesar(String descripcion) {
            System.out.println("✅ Excusa trivial procesada automáticamente: " + descripcion);
            System.out.println("📝 No requiere escalación - aprobada por defecto");
        }
        @Override
        public String obtenerMensajeAprobacion(String descripcion) {
            return "Su excusa trivial ha sido aprobada automáticamente.";
        }
    },
    MODERADA {
        @Override
        public boolean debeEscalar(String descripcion) { return false; }
        @Override
        public void procesar(String descripcion) {
            System.out.println("⚠️ Excusa moderada requiere revisión: " + descripcion);
            System.out.println("🔍 Se enviará consulta para verificar la situación");
        }
        @Override
        public boolean requiereConsultaExterna(String descripcion) {
            String desc = descripcion.toLowerCase();
            return desc.contains("luz") || desc.contains("energía") || desc.contains("energia") ||
                   desc.contains("familiar") || desc.contains("familia");
        }
        @Override
        public String obtenerTipoConsulta(String descripcion) {
            String desc = descripcion.toLowerCase();
            if (desc.contains("luz") || desc.contains("energía") || desc.contains("energia")) {
                return "CONSULTA_EDESUR";
            } else if (desc.contains("familiar") || desc.contains("familia")) {
                return "CONSULTA_FAMILIAR";
            }
            return "CONSULTA_GENERAL";
        }
    },
    COMPLEJA {
        @Override
        public boolean debeEscalar(String descripcion) { return true; }
        @Override
        public void procesar(String descripcion) {
            System.out.println("🚨 Excusa compleja detectada: " + descripcion);
            System.out.println("📋 Requiere investigación adicional y posible escalación");
        }
        @Override
        public boolean requiereInvestigacionPolicial(String descripcion) {
            String desc = descripcion.toLowerCase();
            return desc.contains("robo") || desc.contains("ladrón") || desc.contains("ladron") ||
                   desc.contains("asalto") || desc.contains("secuestro");
        }
        @Override
        public boolean requiereDocumentacionMedica(String descripcion) {
            String desc = descripcion.toLowerCase();
            return desc.contains("accidente") || desc.contains("lesión") || desc.contains("lesion") ||
                   desc.contains("fractura") || desc.contains("hospital");
        }
        @Override
        public String obtenerTipoDocumentacionRequerida(String descripcion) {
            if (requiereInvestigacionPolicial(descripcion)) {
                return "DENUNCIA_POLICIAL";
            } else if (requiereDocumentacionMedica(descripcion)) {
                return "CERTIFICADO_MEDICO";
            }
            return "DOCUMENTACION_ADICIONAL";
        }
    },
    INVEROSIMIL {
        @Override
        public boolean debeEscalar(String descripcion) { return true; }
        @Override
        public void procesar(String descripcion) {
            System.out.println("🤖 Excusa inverosímil detectada: " + descripcion);
            System.out.println("🚫 Rechazada automáticamente por ser imposible");
        }
        @Override
        public boolean esExcusaFantastica(String descripcion) {
            String desc = descripcion.toLowerCase();
            return desc.contains("alien") || desc.contains("fantasma") || desc.contains("dragón") ||
                   desc.contains("dragon") || desc.contains("mago") || desc.contains("hechizo");
        }
        @Override
        public boolean esExcusaCientificamenteImposible(String descripcion) {
            String desc = descripcion.toLowerCase();
            return desc.contains("viaje en el tiempo") || desc.contains("teletransporte") ||
                   desc.contains("invisibilidad") || desc.contains("superpoder");
        }
        @Override
        public String obtenerRazonRechazo(String descripcion) {
            if (esExcusaFantastica(descripcion)) {
                return "EXCUSA_FANTASTICA_IMPOSIBLE";
            } else if (esExcusaCientificamenteImposible(descripcion)) {
                return "EXCUSA_CIENTIFICAMENTE_IMPOSIBLE";
            }
            return "EXCUSA_INVEROSIMIL_GENERAL";
        }
        @Override
        public void registrarRechazo(String descripcion) {
            System.out.println("📝 Registrando rechazo de excusa inverosímil en el sistema");
            System.out.println("⚠️ Se notificará al empleado sobre la imposibilidad de la excusa");
        }
    };

    // Métodos por defecto (pueden ser sobrescritos)
    public boolean debeEscalar(String descripcion) { return false; }
    public void procesar(String descripcion) {}
    public String obtenerMensajeAprobacion(String descripcion) { return ""; }
    public boolean requiereConsultaExterna(String descripcion) { return false; }
    public String obtenerTipoConsulta(String descripcion) { return ""; }
    public boolean requiereInvestigacionPolicial(String descripcion) { return false; }
    public boolean requiereDocumentacionMedica(String descripcion) { return false; }
    public String obtenerTipoDocumentacionRequerida(String descripcion) { return ""; }
    public boolean esExcusaFantastica(String descripcion) { return false; }
    public boolean esExcusaCientificamenteImposible(String descripcion) { return false; }
    public String obtenerRazonRechazo(String descripcion) { return ""; }
    public void registrarRechazo(String descripcion) {}

    public static boolean isValid(String value) {
        for (TipoExcusa tipo : values()) {
            if (tipo.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
