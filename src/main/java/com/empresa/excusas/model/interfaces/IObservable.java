package com.empresa.excusas.model.interfaces;

public interface IObservable {
    void agregarObserver(ObserverProntuario observer);
    void removerObserver(ObserverProntuario observer);
    void notificarObservers();
} 