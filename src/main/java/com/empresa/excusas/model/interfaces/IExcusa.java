package com.empresa.excusas.model.interfaces;

import com.empresa.excusas.model.clasesAbstractas.Empleado;

public interface IExcusa {
    Empleado getEmpleado();
    String getDescripcion();
    boolean debeEscalar();
} 