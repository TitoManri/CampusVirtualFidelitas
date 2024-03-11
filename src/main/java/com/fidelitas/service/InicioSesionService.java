package com.fidelitas.service;

import com.fidelitas.dao.EstudianteDao;
import com.fidelitas.domain.Estudiante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public interface InicioSesionService {
    
    boolean verificarCredenciales(String correo, String contrasena);

}
