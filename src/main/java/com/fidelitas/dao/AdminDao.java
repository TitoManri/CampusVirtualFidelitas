package com.fidelitas.dao;

import com.fidelitas.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin, Integer> {
    Admin findByCorreo(String correo);
}
