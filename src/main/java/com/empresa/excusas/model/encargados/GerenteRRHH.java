package com.empresa.excusas.model.encargados;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.ServicioEmail;
import com.empresa.excusas.model.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.model.interfaces.EmailSender;
import com.empresa.excusas.model.interfaces.ModoOperacion;
import com.empresa.excusas.model.tiposExcusas.ExcusaCompleja;

public class GerenteRRHH extends EncargadoBase {

    public GerenteRRHH(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        super(nombre, email, legajo, modoOperacion);
    }

    @Override
    public boolean puedeManejar(Excusa excusa) {
        return excusa.getTipoExcusa() instanceof ExcusaCompleja;
    }

    @Override
    public void procesar(Excusa excusa) {
        System.out.println("👔 " + this.getNombre() + " (Gerente de RRHH) procesando excusa: " + excusa.getTipoExcusa().getDescripcion());
        modoOperacion();
        
        // Usar los métodos de la excusa (Tell, Don't Ask)
        ExcusaCompleja excusaCompleja = (ExcusaCompleja) excusa.getTipoExcusa();
        excusaCompleja.procesarExcusaCompleja();
        
        EmailSender emailSender = new ServicioEmail();
        
        if (excusaCompleja.requiereInvestigacionPolicial()) {
            System.out.println("🚔 Requiere investigación policial");
            emailSender.enviarEmail(
                    excusa.getEmpleado().getEmail(),
                    this.getEmail(),
                    "Documentación requerida",
                    "Por favor, presente la denuncia policial correspondiente."
            );
        } else if (excusaCompleja.requiereDocumentacionMedica()) {
            System.out.println("🏥 Requiere documentación médica");
            emailSender.enviarEmail(
                    excusa.getEmpleado().getEmail(),
                    this.getEmail(),
                    "Documentación médica requerida",
                    "Por favor, presente el certificado médico correspondiente."
            );
        } else {
            System.out.println("📋 Excusa compleja procesada y archivada.");
            emailSender.enviarEmail(
                    excusa.getEmpleado().getEmail(),
                    this.getEmail(),
                    "Excusa compleja procesada",
                    "Su excusa compleja ha sido procesada y requiere documentación adicional."
            );
        }
    }
}
