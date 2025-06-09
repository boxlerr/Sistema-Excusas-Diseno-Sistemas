package com.empresa.excusas.clases.encargados;

import com.empresa.excusas.clases.Excusa;
import com.empresa.excusas.clases.tiposExcusas.ExcusaCompleja;
import com.empresa.excusas.clasesAbstractas.EncargadoBase;
import com.empresa.excusas.interfaces.ModoOperacion;

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
        System.out.println("Excusa compleja procesada y archivada.");
    }
}
