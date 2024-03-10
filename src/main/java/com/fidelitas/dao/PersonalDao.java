package com.fidelitas.dao;

import com.fidelitas.domain.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDao extends JpaRepository <Personal, Long>{ 
    Personal findByCorreo(String correo);
}

