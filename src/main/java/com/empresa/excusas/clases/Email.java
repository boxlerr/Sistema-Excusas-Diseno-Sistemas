package com.empresa.excusas.clases;

import com.empresa.excusas.interfaces.EmailSender;

public class Email implements EmailSender {

    @Override
    public void enviarEmail(String destino, String origen, String asunto, String cuerpo) {
        System.out.println("=== EMAIL ENVIADO ===");
        System.out.println("De: " + origen);
        System.out.println("Para: " + destino);
        System.out.println("Asunto: " + asunto);
        System.out.println("Cuerpo: " + cuerpo);
        System.out.println("====================");
    }
}
