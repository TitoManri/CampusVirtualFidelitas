package com.fidelitas.dao;

import com.fidelitas.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteDao extends JpaRepository <Estudiante, Long>{ 
    Estudiante findByCorreo(String correo);
}
