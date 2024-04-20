/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.dao;

import com.fidelitas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
}
