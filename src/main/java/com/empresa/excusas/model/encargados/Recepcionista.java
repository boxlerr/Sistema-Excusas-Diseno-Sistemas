package com.empresa.excusas.model.encargados;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.ServicioEmail;
import com.empresa.excusas.model.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.model.clasesAbstractas.TipoExcusa;
import com.empresa.excusas.model.interfaces.EmailSender;
import com.empresa.excusas.model.interfaces.ModoOperacion;

public class Recepcionista extends EncargadoBase {

    public Recepcionista(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        super(nombre, email, legajo, modoOperacion);
    }

    @Override
    public boolean puedeManejar(Excusa excusa) {
        // Solo acepta excusas TRIVIALES
        return TipoExcusa.TRIVIAL.name().equalsIgnoreCase(excusa.getTipoExcusa());
    }

    @Override
    public void procesar(Excusa excusa) {
        System.out.println(getNombre() + " (Recepcionista) procesando excusa trivial: " + excusa.getTipoExcusa() + " - " + excusa.getDescripcion());
        excusa.setEstado("APROBADA");
        // Aquí podrías agregar lógica adicional si lo deseas
    }
}
