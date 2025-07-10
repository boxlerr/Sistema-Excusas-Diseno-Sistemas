package com.empresa.excusas.model;

import java.util.ArrayList;
import java.util.List;

import com.empresa.excusas.model.interfaces.IAdministradorProntuarios;
import com.empresa.excusas.model.interfaces.ObserverProntuario;

public class AdministradorProntuarios extends Observable implements IAdministradorProntuarios {
    private static AdministradorProntuarios instancia;
    private List<Prontuario> prontuarios;

    private AdministradorProntuarios() {
        prontuarios = new ArrayList<>();
    }

    public static synchronized AdministradorProntuarios getInstancia() {
        if (instancia == null) {
            instancia = new AdministradorProntuarios();
        }
        return instancia;
    }

    @Override
    public void agregarProntuario(Prontuario prontuario) {
        prontuarios.add(prontuario);
        System.out.println("Prontuario agregado: " + prontuario);
        notificarObserversConProntuario(prontuario);
    }

    @Override
    public void agregarObserver(ObserverProntuario observer) {
        super.agregarObserver(observer);
    }

    @Override
    public void removerObserver(ObserverProntuario observer) {
        super.removerObserver(observer);
    }

    @Override
    public void notificarObservers(Prontuario prontuario) {
        notificarObserversConProntuario(prontuario);
    }

    public List<Prontuario> getProntuarios() {
        return new ArrayList<>(prontuarios);
    }
}
