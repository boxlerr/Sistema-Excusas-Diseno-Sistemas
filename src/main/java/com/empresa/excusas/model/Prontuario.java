package com.empresa.excusas.model;

public class Prontuario {
    private String nombreEmpleado;
    private String emailEmpleado;
    private int legajoEmpleado;
    private Excusa excusa;

    public Prontuario(String nombreEmpleado, String emailEmpleado, int legajoEmpleado, Excusa excusa) {
        this.nombreEmpleado = nombreEmpleado;
        this.emailEmpleado = emailEmpleado;
        this.legajoEmpleado = legajoEmpleado;
        this.excusa = excusa;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public String getEmailEmpleado() {
        return emailEmpleado;
    }

    public int getLegajoEmpleado() {
        return legajoEmpleado;
    }

    public Excusa getExcusa() {
        return excusa;
    }

    @Override
    public String toString() {
        return "Prontuario{" +
                "nombreEmpleado='" + nombreEmpleado + '\'' +
                ", emailEmpleado='" + emailEmpleado + '\'' +
                ", legajoEmpleado=" + legajoEmpleado +
                ", excusa=" + excusa +
                '}';
    }
}
