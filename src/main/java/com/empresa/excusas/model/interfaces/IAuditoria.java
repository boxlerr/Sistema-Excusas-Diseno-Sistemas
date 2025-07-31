package com.empresa.excusas.model.interfaces;

import com.empresa.excusas.model.Excusa;

public interface IAuditoria {
    void registrarAuditoria(String nombreEncargado, String tipoExcusa, String resultado);
} 