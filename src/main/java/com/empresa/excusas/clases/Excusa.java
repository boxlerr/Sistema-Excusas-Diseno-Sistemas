package com.empresa.excusas.clases;

import com.empresa.excusas.clasesAbstractas.ExcusaBase;
import com.empresa.excusas.clasesAbstractas.TipoExcusa;

public class Excusa extends ExcusaBase {
    private TipoExcusa tipoExcusa;

    public Excusa(com.empresa.excusas.clasesAbstractas.Empleado empleado, String descripcion, TipoExcusa tipoExcusa) {
        super(empleado, descripcion);
        this.tipoExcusa = tipoExcusa;
    }

    public TipoExcusa getTipoExcusa() {
        return tipoExcusa;
    }

    public void setTipoExcusa(TipoExcusa tipoExcusa) {
        this.tipoExcusa = tipoExcusa;
    }

    @Override
    public String toString() {
        return "Excusa{" +
                "empleado=" + getEmpleado() +
                ", descripcion='" + getDescripcion() + '\'' +
                ", tipoExcusa=" + tipoExcusa +
                '}';
    }
}
