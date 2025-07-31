package com.empresa.excusas.model;

import com.empresa.excusas.model.interfaces.IEncargado;
import com.empresa.excusas.model.interfaces.IAuditoria;
import com.empresa.excusas.model.encargados.*;
import com.empresa.excusas.model.interfaces.ModoOperacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncargadoFactory {
    
    @Autowired
    private IAuditoria auditoria;
    
    public IEncargado crearRecepcionistaConAuditoria(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        Recepcionista recepcionista = new Recepcionista(nombre, email, legajo, modoOperacion);
        EncargadoConAuditoria encargadoConAuditoria = new EncargadoConAuditoria(auditoria);
        encargadoConAuditoria.setEncargado(recepcionista);
        return encargadoConAuditoria;
    }
    
    public IEncargado crearSupervisorConAuditoria(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        SupervisorArea supervisor = new SupervisorArea(nombre, email, legajo, modoOperacion);
        EncargadoConAuditoria encargadoConAuditoria = new EncargadoConAuditoria(auditoria);
        encargadoConAuditoria.setEncargado(supervisor);
        return encargadoConAuditoria;
    }
    
    public IEncargado crearGerenteRRHHConAuditoria(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        GerenteRRHH gerente = new GerenteRRHH(nombre, email, legajo, modoOperacion);
        EncargadoConAuditoria encargadoConAuditoria = new EncargadoConAuditoria(auditoria);
        encargadoConAuditoria.setEncargado(gerente);
        return encargadoConAuditoria;
    }
    
    public IEncargado crearCEOConAuditoria(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        CEO ceo = new CEO(nombre, email, legajo, modoOperacion);
        EncargadoConAuditoria encargadoConAuditoria = new EncargadoConAuditoria(auditoria);
        encargadoConAuditoria.setEncargado(ceo);
        return encargadoConAuditoria;
    }
    
    public IEncargado crearEncargadoPorDefectoConAuditoria(String nombre, String email, int legajo, ModoOperacion modoOperacion) {
        EncargadoPorDefecto encargadoDefecto = new EncargadoPorDefecto(nombre, email, legajo, modoOperacion);
        EncargadoConAuditoria encargadoConAuditoria = new EncargadoConAuditoria(auditoria);
        encargadoConAuditoria.setEncargado(encargadoDefecto);
        return encargadoConAuditoria;
    }
} 