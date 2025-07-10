package com.empresa.excusas.model.encargados;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.ServicioEmail;
import com.empresa.excusas.model.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.model.interfaces.EmailSender;
import com.empresa.excusas.model.interfaces.ModoOperacion;
import com.empresa.excusas.model.tiposExcusas.ExcusaModerada;

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

        // Usar el servicio de email separado
        EmailSender emailSender = new ServicioEmail();
        ExcusaModerada excusaModerada = (ExcusaModerada) excusa.getTipoExcusa();
        
        // Usar los métodos de la excusa (Tell, Don't Ask)
        excusaModerada.procesarExcusaModerada();

        if (excusaModerada.requiereConsultaExterna()) {
            String tipoConsulta = excusaModerada.obtenerTipoConsulta();
            
            if ("CONSULTA_EDESUR".equals(tipoConsulta)) {
                System.out.println("🔍 Detectada excusa de corte de energía. Consultando con EDESUR...");
                emailSender.enviarEmail(
                        "EDESUR@mailfake.com.ar",
                        this.getEmail(),
                        "Consulta por corte de luz",
                        "Consulta si hubo corte de luz en el área del empleado " + excusa.getEmpleado().getNombre()
                );
            } else if ("CONSULTA_FAMILIAR".equals(tipoConsulta)) {
                System.out.println("❤️ Detectada excusa de cuidado familiar. Preguntando si todo está bien...");
                emailSender.enviarEmail(
                        excusa.getEmpleado().getEmail(),
                        this.getEmail(),
                        "¿Todo está bien?",
                        "¿Todo está bien?"
                );
            }
        } else {
            System.out.println("📋 Procesando excusa moderada general...");
            emailSender.enviarEmail(
                    excusa.getEmpleado().getEmail(),
                    this.getEmail(),
                    "Excusa procesada",
                    "Su excusa ha sido procesada y aprobada."
            );
        }
    }
}
