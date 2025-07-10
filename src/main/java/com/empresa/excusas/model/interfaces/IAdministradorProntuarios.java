package com.empresa.excusas.model.interfaces;

import com.empresa.excusas.model.Prontuario;

public interface IAdministradorProntuarios {
    void agregarProntuario(Prontuario prontuario);
    void agregarObserver(ObserverProntuario observer);
    void removerObserver(ObserverProntuario observer);
    void notificarObservers(Prontuario prontuario);
}
