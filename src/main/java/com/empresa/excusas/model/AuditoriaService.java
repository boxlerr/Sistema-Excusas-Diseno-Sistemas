package com.empresa.excusas.model;

import com.empresa.excusas.model.interfaces.IAuditoria;
import org.springframework.stereotype.Service;

@Service
public class AuditoriaService implements IAuditoria {
    
    @Override
    public void registrarAuditoria(String nombreEncargado, String tipoExcusa, String resultado) {
        System.out.println("[AUDITORÍA] Encargado: " + nombreEncargado + " - Excusa: " + tipoExcusa + " - Resultado: " + resultado);
    }
} 