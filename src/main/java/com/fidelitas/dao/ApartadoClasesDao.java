package com.fidelitas.dao;
import com.fidelitas.domain.ApartadoClases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartadoClasesDao extends JpaRepository<ApartadoClases, Long> {
    
}
