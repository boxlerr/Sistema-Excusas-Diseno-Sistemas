package com.empresa.excusas.model;

import com.empresa.excusas.model.clasesAbstractas.Empleado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmpleadoExcusador extends Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public EmpleadoExcusador() {
        super();
    }
    
    public EmpleadoExcusador(String nombre, String email, int legajo) {
        super(nombre, email, legajo);
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}
