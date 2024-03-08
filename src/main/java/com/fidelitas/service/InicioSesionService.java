package com.fidelitas.service;

import com.fidelitas.dao.InicioSesionDao;
import com.fidelitas.domain.InicioSesion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public interface InicioSesionService {
    
    boolean verificarCredenciales(String correo, String contrasena);

}
