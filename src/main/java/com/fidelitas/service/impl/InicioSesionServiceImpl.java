package com.fidelitas.service.impl;

import com.fidelitas.dao.AdminDao;
import com.fidelitas.dao.EstudianteDao;
import com.fidelitas.domain.Admin;
import com.fidelitas.domain.Estudiante;
import com.fidelitas.service.InicioSesionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InicioSesionServiceImpl implements InicioSesionService {

    @Autowired
    private EstudianteDao estudianteDao;
    @Autowired
    private AdminDao adminDao;

    @Override
    public boolean verificarCredenciales(String correo, String contrasena) {
        // Buscar el inicio de sesión por correo
        Estudiante estudiante = estudianteDao.findByCorreo(correo);

        // Verificar si el inicio de sesión existe y si la contraseña coincide
        return estudiante != null && estudiante.getContrasena().equals(contrasena);
    }

    // suponiendo que el correo de un administrador no existe en la tabla de estudiantes (admin@example.com y root@example.com, son correos de administradores)
    // No permitir crear estudiantes con esos correos, ya sea evitando especificamente esos 2 o obteniendo los correos de los administradores de la tabla de administradores y evitando esos correos
    @Override
    public String obtenerElPosibleTipoDeUsuario(String correo) {
        Estudiante estudiante = estudianteDao.findByCorreo(correo);
        if (estudiante != null) {
            return "estudiante";
        } else {
            // si no es estudiante se verifica si es admin
            return adminDao.findByCorreo(correo) != null ? "admin" : null;
        }
    }

    @Override
    public Estudiante loggearEstudiante(String correo, String contrasena) {
        Estudiante estudiante = estudianteDao.findByCorreo(correo);

        if (estudiante.getContrasena().equals(contrasena)) {
            return estudiante;
        } else {
            return null;
        }
    }

    @Override
    public Admin loggearAdmin(String correo, String contrasena) {
        Admin admin = adminDao.findByCorreo(correo);

        if (admin.getContrasena().equals(contrasena)) {
            return admin;
        } else {
            return null;
        }
    }
}