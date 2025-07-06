package com.empresa.excusas.interfaces;

import com.empresa.excusas.clasesAbstractas.Empleado;

public interface IExcusa {
    Empleado getEmpleado();
    String getDescripcion();
    boolean debeEscalar();
} 