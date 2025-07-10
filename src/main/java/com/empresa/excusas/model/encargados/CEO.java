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
        return excusa.getTipoExcusa() instanceof ExcusaInverosimil;
    }

    @Override
    public void procesar(Excusa excusa) {
        System.out.println("🎩 " + this.getNombre() + " (CEO) procesando excusa extremadamente inverosímil: " + excusa.getTipoExcusa().getDescripcion());
        modoOperacion();

        // Usar los métodos de la excusa (Tell, Don't Ask)
        ExcusaInverosimil excusaInverosimil = (ExcusaInverosimil) excusa.getTipoExcusa();
        excusaInverosimil.procesarExcusaInverosimil();
        excusaInverosimil.registrarRechazo();

        // EXACTAMENTE como dice la consigna: "Aprobado por creatividad"
        EmailSender emailSender = new ServicioEmail();
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
