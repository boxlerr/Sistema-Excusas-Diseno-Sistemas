package com.empresa.excusas.repository;

import com.empresa.excusas.model.Encargado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EncargadoRepository extends JpaRepository<Encargado, Long> {
    
    Optional<Encargado> findByLegajo(int legajo);
    
    Optional<Encargado> findByEmail(String email);
    
    boolean existsByLegajo(int legajo);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT MAX(e.legajo) FROM Encargado e")
    Integer findMaxLegajo();
}
