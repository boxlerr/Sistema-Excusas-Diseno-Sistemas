package com.empresa.excusas.model;

import com.empresa.excusas.model.interfaces.IEncargado;
import com.empresa.excusas.model.interfaces.IAuditoria;
import com.empresa.excusas.model.Excusa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncargadoConAuditoria implements IEncargado {
    
    private IEncargado encargado;
    private final IAuditoria auditoria;
    
    @Autowired
    public EncargadoConAuditoria(IAuditoria auditoria) {
        this.auditoria = auditoria;
    }
    
    public void setEncargado(IEncargado encargado) {
        this.encargado = encargado;
    }
    
    @Override
    public void manejarExcusa(Excusa excusa) {
        if (encargado == null) {
            throw new IllegalStateException("Encargado no configurado");
        }
        
        // Verificar si el encargado puede manejar la excusa
        if (encargado.puedeManejar(excusa)) {
            // Registrar auditoría antes de procesar
            String nombreEncargado = obtenerNombreEncargado();
            String tipoExcusa = excusa.getTipoExcusa();
            
            // Procesar la excusa
            encargado.procesar(excusa);
            
            // Determinar el resultado basado en el estado de la excusa
            String resultado = determinarResultado(excusa);
            
            // Registrar auditoría
            auditoria.registrarAuditoria(nombreEncargado, tipoExcusa, resultado);
        } else {
            // Si no puede manejar, derivar al siguiente
            if (encargado.getSiguiente() != null) {
                encargado.manejarExcusa(excusa);
            } else {
                // No hay siguiente encargado, registrar como derivada
                String nombreEncargado = obtenerNombreEncargado();
                String tipoExcusa = excusa.getTipoExcusa();
                auditoria.registrarAuditoria(nombreEncargado, tipoExcusa, "DERIVADA");
            }
        }
    }
    
    private String obtenerNombreEncargado() {
        if (encargado instanceof com.empresa.excusas.model.clasesAbstractas.EncargadoBase) {
            return ((com.empresa.excusas.model.clasesAbstractas.EncargadoBase) encargado).getNombre();
        }
        return "Encargado";
    }
    
    private String determinarResultado(Excusa excusa) {
        String estado = excusa.getEstado();
        if ("ACEPTADA".equals(estado) || "APROBADA".equals(estado)) {
            return "ACEPTADA";
        } else if ("RECHAZADA".equals(estado) || "DENEGADA".equals(estado)) {
            return "RECHAZADA";
        } else {
            return "DERIVADA";
        }
    }
    
    @Override
    public void setSiguiente(IEncargado siguiente) {
        if (encargado != null) {
            encargado.setSiguiente(siguiente);
        }
    }
    
    @Override
    public void modoOperacion() {
        if (encargado != null) {
            encargado.modoOperacion();
        }
    }
    
    @Override
    public boolean puedeManejar(Excusa excusa) {
        return encargado != null && encargado.puedeManejar(excusa);
    }
    
    @Override
    public void procesar(Excusa excusa) {
        if (encargado != null) {
            encargado.procesar(excusa);
        }
    }
    
    public IEncargado getSiguiente() {
        return encargado != null ? encargado.getSiguiente() : null;
    }
} 