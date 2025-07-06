package com.empresa.excusas.clases.encargados;

import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clases.ServicioEmail;
import com.empresa.excusas.clases.tiposExcusas.ExcusaTrivial;
import com.empresa.excusas.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.interfaces.EmailSender;
import com.empresa.excusas.interfaces.ModoOperacion;

public class Recepcionista extends EncargadoBase {

    public Recepcionista(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        super(nombre, email, legajo, modoOperacion);
    }

    @Override
    public boolean puedeManejar(Excusa excusa) {
        return excusa.getTipoExcusa() instanceof ExcusaTrivial;
    }

    @Override
    public void procesar(Excusa excusa) {
        System.out.println("🏢 " + this.getNombre() + " (Recepcionista) procesando excusa: " + excusa.getTipoExcusa().getDescripcion());
        modoOperacion();

        // Usar los métodos de la excusa (Tell, Don't Ask)
        ExcusaTrivial excusaTrivial = (ExcusaTrivial) excusa.getTipoExcusa();
        excusaTrivial.procesarExcusaTrivial();

        // EXACTAMENTE como dice la consigna: asunto "motivo demora" y mensaje "la licencia fue aceptada"
        EmailSender emailSender = new ServicioEmail();
        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "motivo demora",
                "la licencia fue aceptada"
        );
    }
}
