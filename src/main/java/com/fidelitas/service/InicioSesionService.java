package com.fidelitas.service;

import com.fidelitas.dao.EstudianteDao;
import com.fidelitas.domain.Admin;
import com.fidelitas.domain.Estudiante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public interface InicioSesionService {
    Admin iniciarSesionAdmin(String correo, String contrasena);
    Estudiante iniciarSesionEstudiante(String correo, String contrasena);
}

