package com.empresa.excusas.model.encargados;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.ServicioEmail;
import com.empresa.excusas.model.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.model.interfaces.EmailSender;
import com.empresa.excusas.model.interfaces.ModoOperacion;

public class EncargadoPorDefecto extends EncargadoBase {

    public EncargadoPorDefecto(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        super(nombre, email, legajo, modoOperacion);
    }

    @Override
    public boolean puedeManejar(Excusa excusa) {
        // Puede manejar cualquier tipo de excusa
        return true;
    }

    @Override
    public void procesar(Excusa excusa) {
        System.out.println(getNombre() + " (Encargado por Defecto) procesando excusa: " + excusa.getTipoExcusa() + " - " + excusa.getDescripcion());
        excusa.setEstado("EN REVISION");
        // Aquí podrías agregar lógica adicional si lo deseas
    }
}
