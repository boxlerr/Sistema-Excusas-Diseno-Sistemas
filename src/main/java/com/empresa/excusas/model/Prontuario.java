package com.empresa.excusas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prontuarios")
public class Prontuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombreEmpleado;
    
    @Column(nullable = false)
    private String emailEmpleado;
    
    @Column(nullable = false)
    private int legajoEmpleado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "excusa_id", nullable = false)
    private Excusa excusa;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public Prontuario() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public Prontuario(String nombreEmpleado, String emailEmpleado, int legajoEmpleado, Excusa excusa) {
        this.nombreEmpleado = nombreEmpleado;
        this.emailEmpleado = emailEmpleado;
        this.legajoEmpleado = legajoEmpleado;
        this.excusa = excusa;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getEmailEmpleado() {
        return emailEmpleado;
    }

    public void setEmailEmpleado(String emailEmpleado) {
        this.emailEmpleado = emailEmpleado;
    }

    public int getLegajoEmpleado() {
        return legajoEmpleado;
    }

    public void setLegajoEmpleado(int legajoEmpleado) {
        this.legajoEmpleado = legajoEmpleado;
    }

    public Excusa getExcusa() {
        return excusa;
    }

    public void setExcusa(Excusa excusa) {
        this.excusa = excusa;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Prontuario{" +
                "id=" + id +
                ", nombreEmpleado='" + nombreEmpleado + '\'' +
                ", emailEmpleado='" + emailEmpleado + '\'' +
                ", legajoEmpleado=" + legajoEmpleado +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
