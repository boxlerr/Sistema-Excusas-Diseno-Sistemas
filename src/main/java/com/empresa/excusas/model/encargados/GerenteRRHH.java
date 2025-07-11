package com.empresa.excusas.model.encargados;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.ServicioEmail;
import com.empresa.excusas.model.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.model.clasesAbstractas.TipoExcusa;
import com.empresa.excusas.model.interfaces.EmailSender;
import com.empresa.excusas.model.interfaces.ModoOperacion;

public class GerenteRRHH extends EncargadoBase {

    public GerenteRRHH(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        super(nombre, email, legajo, modoOperacion);
    }

    @Override
    public boolean puedeManejar(Excusa excusa) {
        // Solo acepta excusas MODERADAS
        return TipoExcusa.MODERADA.name().equalsIgnoreCase(excusa.getTipoExcusa());
    }

    @Override
    public void procesar(Excusa excusa) {
        System.out.println(getNombre() + " (Gerente RRHH) procesando excusa moderada: " + excusa.getTipoExcusa() + " - " + excusa.getDescripcion());
        excusa.setEstado("EN REVISION");
        // Aquí podrías agregar lógica adicional si lo deseas
    }
}
