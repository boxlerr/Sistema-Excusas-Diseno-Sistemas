package com.empresa.excusas.model.interfaces;

public interface EmailSender {
    void enviarEmail(String unEmailDestino, String unEmailOrigen, String unAsunto, String unCuerpo);
}
