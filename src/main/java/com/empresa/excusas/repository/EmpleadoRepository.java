package com.empresa.excusas.repository;

import com.empresa.excusas.model.EmpleadoExcusador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoExcusador, Long> {
    
    Optional<EmpleadoExcusador> findByLegajo(int legajo);
    
    Optional<EmpleadoExcusador> findByEmail(String email);
    
    boolean existsByLegajo(int legajo);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT e FROM EmpleadoExcusador e WHERE e.nombre LIKE %:nombre%")
    java.util.List<EmpleadoExcusador> findByNombreContaining(@Param("nombre") String nombre);
}
