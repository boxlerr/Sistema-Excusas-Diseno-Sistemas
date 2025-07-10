package com.empresa.excusas.repository;

import com.empresa.excusas.model.Excusa;
import com.empresa.excusas.model.EmpleadoExcusador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExcusaRepository extends JpaRepository<Excusa, Long> {
    
    List<Excusa> findByEmpleado(EmpleadoExcusador empleado);
    
    List<Excusa> findByTipoExcusa(String tipoExcusa);
    
    List<Excusa> findByEstado(String estado);
    
    List<Excusa> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);
    
    @Query("SELECT e FROM Excusa e WHERE e.empleado.legajo = :legajo")
    List<Excusa> findByEmpleadoLegajo(@Param("legajo") int legajo);
    
    @Query("SELECT COUNT(e) FROM Excusa e WHERE e.empleado = :empleado")
    long countByEmpleado(@Param("empleado") EmpleadoExcusador empleado);
}
