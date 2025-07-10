package com.empresa.excusas.model.modoOperacion;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.ServicioEmail;
import com.empresa.excusas.model.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.model.interfaces.EmailSender;
import com.empresa.excusas.model.interfaces.IEncargado;
import com.empresa.excusas.model.interfaces.ModoOperacion;

public class ModoProductivo implements ModoOperacion {

    @Override
    public void modoOperacion() {
        System.out.println("Modo: PRODUCTIVO");
    }

    @Override
    public void manejarExcusa(EncargadoBase encargado, Excusa excusa) {
        if (encargado.puedeManejar(excusa)) {
            // Solo envía email al CTO si este encargado va a procesar la excusa
            enviarEmailAlCTO(encargado, excusa);
            encargado.procesar(excusa);
        } else if (encargado.getSiguiente() != null) {
            // Si no puede manejarla, simplemente la pasa al siguiente sin enviar email
            encargado.getSiguiente().manejarExcusa(excusa);
        } else {
            System.out.println("Excusa rechazada: necesitamos pruebas contundentes");
        }
    }

    private void enviarEmailAlCTO(EncargadoBase encargado, Excusa excusa) {
        EmailSender emailSender = new ServicioEmail();
        emailSender.enviarEmail(
                "cto@empresa.com",
                encargado.getEmail(),
                "Excusa recibida",
                "Se ha recibido una excusa de " + excusa.getEmpleado().getNombre() +
                        " con el motivo: " + excusa.getTipoExcusa()
        );
    }
}
