package com.empresa.excusas.model.encargados;

import com.empresa.excusas.model.AdministradorProntuarios;
import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.Prontuario;
import com.empresa.excusas.model.ServicioEmail;
import com.empresa.excusas.model.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.model.interfaces.EmailSender;
import com.empresa.excusas.model.interfaces.ModoOperacion;
import com.empresa.excusas.model.interfaces.ObserverProntuario;
import com.empresa.excusas.model.tiposExcusas.ExcusaInverosimil;

public class CEO extends EncargadoBase implements ObserverProntuario {

    public CEO(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        super(nombre, email, legajo, modoOperacion);
        AdministradorProntuarios.getInstancia().agregarObserver(this);
    }

    @Override
    public boolean puedeManejar(Excusa excusa) {
        // Solo acepta excusas EXTREMADAMENTE inverosímiles
        return "INVEROSIMIL".equalsIgnoreCase(excusa.getTipoExcusa());
    }

    @Override
    public void procesar(Excusa excusa) {
        System.out.println(getNombre() + " (CEO) procesando excusa extremadamente inverosímil: " + excusa.getTipoExcusa() + " - " + excusa.getDescripcion());
        // Aquí podrías agregar lógica adicional si lo deseas
        // Por ejemplo, marcar la excusa como aprobada automáticamente
        excusa.setEstado("APROBADA POR CREATIVIDAD");
        EmailSender emailSender = new ServicioEmail();
        emailSender.enviarEmail(
            excusa.getEmpleado().getEmail(),
            this.getEmail(),
            "Aprobado por creatividad",
            "Su excusa ha sido aprobada por creatividad."
        );
    }

    @Override
    public void actualizar(Prontuario prontuario) {
        // No se notifica a sí mismo
        if (!prontuario.getEmailEmpleado().equals(this.getEmail())) {
            EmailSender emailSender = new ServicioEmail();
            emailSender.enviarEmail(
                    this.getEmail(),
                    "sistema@empresa.com",
                    "Nuevo prontuario generado",
                    "Se ha generado un nuevo prontuario para el empleado: " + prontuario.getNombreEmpleado() +
                            " (Legajo: " + prontuario.getLegajoEmpleado() + ")"
            );
        }
    }
}
