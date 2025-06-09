package com.empresa.excusas.clases.encargados;

import com.empresa.excusas.clases.Email;
import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clases.tiposExcusas.ExcusaModerada;
import com.empresa.excusas.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.interfaces.EmailSender;
import com.empresa.excusas.interfaces.ModoOperacion;

public class SupervisorArea extends EncargadoBase {

    public SupervisorArea(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        super(nombre, email, legajo, modoOperacion);
    }

    @Override
    public boolean puedeManejar(Excusa excusa) {
        return excusa.getTipoExcusa() instanceof ExcusaModerada;
    }

    @Override
    public void procesar(Excusa excusa) {
        System.out.println("👨‍💼 " + this.getNombre() + " (Supervisor de Área) procesando excusa: " + excusa.getTipoExcusa().getDescripcion());
        modoOperacion();

        EmailSender emailSender = new Email();
        String motivoExcusa = excusa.getTipoExcusa().getDescripcion().toLowerCase();

        // Caso específico: Corte de luz - EXACTAMENTE COMO DICE LA CONSIGNA
        if (esExcusaCorteDeEnergia(motivoExcusa)) {
            System.out.println("🔍 Detectada excusa de corte de energía. Consultando con EDESUR...");
            emailSender.enviarEmail(
                    "EDESUR@mailfake.com.ar",
                    this.getEmail(),
                    "Consulta por corte de luz",
                    "Consulta si hubo corte de luz en el área del empleado " + excusa.getEmpleado().getNombre()
            );
        }
        // Caso específico: Cuidado familiar - EXACTAMENTE COMO DICE LA CONSIGNA
        else if (esExcusaCuidadoFamiliar(motivoExcusa)) {
            System.out.println("❤️ Detectada excusa de cuidado familiar. Preguntando si todo está bien...");
            emailSender.enviarEmail(
                    excusa.getEmpleado().getEmail(),
                    this.getEmail(),
                    "¿Todo está bien?",
                    "¿Todo está bien?"
            );
        }
        // Otras excusas moderadas
        else {
            System.out.println("📋 Procesando excusa moderada general...");
            emailSender.enviarEmail(
                    excusa.getEmpleado().getEmail(),
                    this.getEmail(),
                    "Excusa procesada",
                    "Su excusa ha sido procesada y aprobada."
            );
        }
    }

    private boolean esExcusaCorteDeEnergia(String descripcion) {
        return descripcion.contains("cort") && (descripcion.contains("luz") || descripcion.contains("energía") ||
                descripcion.contains("energia") || descripcion.contains("electricidad") || descripcion.contains("apagón") ||
                descripcion.contains("apagon") || descripcion.contains("suministro"));
    }

    private boolean esExcusaCuidadoFamiliar(String descripcion) {
        // Detectar si hay palabras de familia Y enfermedad/cuidado
        boolean tieneRelacionFamiliar = descripcion.contains("familiar") || descripcion.contains("familia") ||
                descripcion.contains("hijo") || descripcion.contains("hija") ||
                descripcion.contains("padre") || descripcion.contains("madre") ||
                descripcion.contains("abuelo") || descripcion.contains("abuela") ||
                descripcion.contains("tío") || descripcion.contains("tio") ||
                descripcion.contains("tía") || descripcion.contains("tia") ||
                descripcion.contains("primo") || descripcion.contains("prima") ||
                descripcion.contains("hermano") || descripcion.contains("hermana") ||
                descripcion.contains("esposo") || descripcion.contains("esposa") ||
                descripcion.contains("pareja");

        boolean tieneSituacionCuidado = descripcion.contains("enfermo") || descripcion.contains("enferma") ||
                descripcion.contains("hospital") || descripcion.contains("médico") ||
                descripcion.contains("medico") || descripcion.contains("cuidar") ||
                descripcion.contains("cuidado") || descripcion.contains("emergencia") ||
                descripcion.contains("internado") || descripcion.contains("internada");

        return tieneRelacionFamiliar && tieneSituacionCuidado;
    }
}
