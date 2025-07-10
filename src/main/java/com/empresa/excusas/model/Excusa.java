package com.empresa.excusas.model;

import com.empresa.excusas.model.clasesAbstractas.ExcusaBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "excusas")
public class Excusa extends ExcusaBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    private EmpleadoExcusador empleado;
    
    @Column(nullable = false)
    private String tipoExcusa;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column
    private String estado = "PENDIENTE";

    public Excusa() {
        super();
    }

    public Excusa(EmpleadoExcusador empleado, String descripcion, String tipoExcusa) {
        super(empleado, descripcion);
        this.empleado = empleado;
        this.tipoExcusa = tipoExcusa;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public EmpleadoExcusador getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoExcusador empleado) {
        this.empleado = empleado;
    }

    public String getTipoExcusa() {
        return tipoExcusa;
    }

    public void setTipoExcusa(String tipoExcusa) {
        this.tipoExcusa = tipoExcusa;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Excusa{" +
                "id=" + id +
                ", empleado=" + empleado +
                ", descripcion='" + getDescripcion() + '\'' +
                ", tipoExcusa='" + tipoExcusa + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", estado='" + estado + '\'' +
                '}';
    }
}
