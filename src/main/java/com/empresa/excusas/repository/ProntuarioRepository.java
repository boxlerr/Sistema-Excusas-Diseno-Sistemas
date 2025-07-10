package com.empresa.excusas.repository;

import com.empresa.excusas.model.Prontuario;
import com.empresa.excusas.model.EmpleadoExcusador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    
    List<Prontuario> findByLegajoEmpleado(int legajo);
    
    List<Prontuario> findByEmailEmpleado(String email);
    
    List<Prontuario> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);
    
    @Query("SELECT p FROM Prontuario p WHERE p.nombreEmpleado LIKE %:nombre%")
    List<Prontuario> findByNombreEmpleadoContaining(@Param("nombre") String nombre);
    
    @Query("SELECT COUNT(p) FROM Prontuario p WHERE p.legajoEmpleado = :legajo")
    long countByLegajoEmpleado(@Param("legajo") int legajo);
}
