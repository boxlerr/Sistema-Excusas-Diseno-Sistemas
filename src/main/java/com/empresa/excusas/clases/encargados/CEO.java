package com.empresa.excusas.clases.encargados;

import com.empresa.excusas.clases.AdministradorProntuarios;
import com.empresa.excusas.clases.Email;
import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clases.Prontuario;
import com.empresa.excusas.clases.tiposExcusas.ExcusaInverosimil;
import com.empresa.excusas.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.interfaces.EmailSender;
import com.empresa.excusas.interfaces.ModoOperacion;
import com.empresa.excusas.interfaces.ObserverProntuario;

public class CEO extends EncargadoBase implements ObserverProntuario {

    public CEO(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        super(nombre, email, legajo, modoOperacion);
        AdministradorProntuarios.getInstancia().agregarObserver(this);
    }

    @Override
    public boolean puedeManejar(Excusa excusa) {
        // Solo acepta excusas EXTREMADAMENTE inverosímiles
        return excusa.getTipoExcusa() instanceof ExcusaInverosimil;
    }

    @Override
    public void procesar(Excusa excusa) {
        System.out.println("🎩 " + this.getNombre() + " (CEO) procesando excusa extremadamente inverosímil: " + excusa.getTipoExcusa().getDescripcion());
        modoOperacion();

        // EXACTAMENTE como dice la consigna: "Aprobado por creatividad"
        EmailSender emailSender = new Email();
        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "Motivo demora",
                "Aprobado por creatividad"
        );

        // Crear prontuario con datos del empleado, la excusa y el nro de legajo
        Prontuario prontuario = new Prontuario(
                excusa.getEmpleado().getNombre(),
                excusa.getEmpleado().getEmail(),
                excusa.getEmpleado().getLegajo(),
                excusa
        );

        System.out.println("📋 Iniciando prontuario para el empleado " + excusa.getEmpleado().getNombre() + " (Legajo: " + excusa.getEmpleado().getLegajo() + ")");
        AdministradorProntuarios.getInstancia().agregarProntuario(prontuario);
    }

    @Override
    public void actualizar(Prontuario prontuario) {
        // No se notifica a sí mismo
        if (!prontuario.getEmailEmpleado().equals(this.getEmail())) {
            EmailSender emailSender = new Email();
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
