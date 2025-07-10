package com.empresa.excusas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "encargados")
public class Encargado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @Column(nullable = false, unique = true)
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;
    
    @Column(nullable = false, unique = true)
    private int legajo;
    
    @Column(nullable = false)
    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;
    
    @Column(nullable = false)
    @NotBlank(message = "El modo es obligatorio")
    private String modo;

    public Encargado() {}

    public Encargado(String nombre, String email, int legajo, String tipo, String modo) {
        this.nombre = nombre;
        this.email = email;
        this.legajo = legajo;
        this.tipo = tipo;
        this.modo = modo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getLegajo() { return legajo; }
    public void setLegajo(int legajo) { this.legajo = legajo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getModo() { return modo; }
    public void setModo(String modo) { this.modo = modo; }

    // DTO interno para Encargado
    public static class EncargadoDTO {
        private Long id;
        private String nombre;
        private String email;
        private int legajo;
        private String tipo;
        private String modo;

        public EncargadoDTO() {}

        public EncargadoDTO(Long id, String nombre, String email, int legajo, String tipo, String modo) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.legajo = legajo;
            this.tipo = tipo;
            this.modo = modo;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public int getLegajo() { return legajo; }
        public void setLegajo(int legajo) { this.legajo = legajo; }

        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }

        public String getModo() { return modo; }
        public void setModo(String modo) { this.modo = modo; }
    }

    @Override
    public String toString() {
        return "Encargado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", legajo=" + legajo +
                ", tipo='" + tipo + '\'' +
                ", modo='" + modo + '\'' +
                '}';
    }
}
