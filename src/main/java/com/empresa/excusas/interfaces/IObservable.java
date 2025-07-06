package com.empresa.excusas.interfaces;

public interface IObservable {
    void agregarObserver(ObserverProntuario observer);
    void removerObserver(ObserverProntuario observer);
    void notificarObservers();
} 