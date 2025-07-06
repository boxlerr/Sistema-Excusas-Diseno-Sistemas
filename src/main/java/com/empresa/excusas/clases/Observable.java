package com.empresa.excusas.clases;

import com.empresa.excusas.interfaces.IObservable;
import com.empresa.excusas.interfaces.ObserverProntuario;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable implements IObservable {
    private List<ObserverProntuario> observers = new ArrayList<>();

    @Override
    public void agregarObserver(ObserverProntuario observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removerObserver(ObserverProntuario observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObservers() {
        for (ObserverProntuario observer : observers) {
            // Aquí necesitaríamos pasar el objeto específico que se actualizó
            // Por simplicidad, pasamos null por ahora
            observer.actualizar(null);
        }
    }

    protected void notificarObserversConProntuario(Prontuario prontuario) {
        for (ObserverProntuario observer : observers) {
            observer.actualizar(prontuario);
        }
    }
} 