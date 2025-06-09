package com.empresa.excusas.interfaces;

import com.empresa.excusas.clases.Prontuario;

public interface IAdministradorProntuarios {
    void agregarProntuario(Prontuario prontuario);
    void agregarObserver(ObserverProntuario observer);
    void removerObserver(ObserverProntuario observer);
    void notificarObservers(Prontuario prontuario);
}
