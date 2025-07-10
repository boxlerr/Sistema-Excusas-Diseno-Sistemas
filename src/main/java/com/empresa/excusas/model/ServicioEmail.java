package com.empresa.excusas.model;

import com.empresa.excusas.model.interfaces.EmailSender;

public class ServicioEmail implements EmailSender {
    
    @Override
    public void enviarEmail(String unEmailDestino, String unEmailOrigen, String unAsunto, String unCuerpo) {
        System.out.println("📧 Enviando email:");
        System.out.println("   De: " + unEmailOrigen);
        System.out.println("   Para: " + unEmailDestino);
        System.out.println("   Asunto: " + unAsunto);
        System.out.println("   Cuerpo: " + unCuerpo);
        System.out.println("   ✅ Email enviado exitosamente");
    }
} 