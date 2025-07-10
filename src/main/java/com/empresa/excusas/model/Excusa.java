package com.empresa.excusas.model;

import com.empresa.excusas.model.clasesAbstractas.ExcusaBase;
import com.empresa.excusas.model.clasesAbstractas.TipoExcusa;

public class Excusa extends ExcusaBase {
    private TipoExcusa tipoExcusa;

    public Excusa(com.empresa.excusas.model.clasesAbstractas.Empleado empleado, String descripcion, TipoExcusa tipoExcusa) {
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
