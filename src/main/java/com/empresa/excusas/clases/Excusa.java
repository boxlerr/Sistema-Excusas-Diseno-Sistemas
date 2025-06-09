package com.empresa.excusas.clases;

import com.empresa.excusas.clasesAbstractas.Empleado;
import com.empresa.excusas.clasesAbstractas.TipoExcusa;

public class Excusa {
    private Empleado empleado;
    private TipoExcusa tipoExcusa;

    public Excusa(Empleado empleado, TipoExcusa tipoExcusa) {
        this.empleado = empleado;
        this.tipoExcusa = tipoExcusa;
    }

    public TipoExcusa getTipoExcusa() {
        return tipoExcusa;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    @Override
    public String toString() {
        return "Excusa{" +
                "empleado=" + empleado +
                ", tipoExcusa=" + tipoExcusa +
                '}';
    }
}
