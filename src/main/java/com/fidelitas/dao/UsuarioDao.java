package com.fidelitas.dao;

import com.fidelitas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository <Usuario, Long>{ 
    
}

