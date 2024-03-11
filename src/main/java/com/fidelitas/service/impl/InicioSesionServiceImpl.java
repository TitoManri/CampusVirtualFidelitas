package com.fidelitas.service.impl;

import com.fidelitas.dao.EstudianteDao;
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

    @Override
    public boolean verificarCredenciales(String correo, String contrasena) {
        // Buscar el inicio de sesión por correo
        Estudiante estudiante = estudianteDao.findByCorreo(correo);

        // Verificar si el inicio de sesión existe y si la contraseña coincide
        return estudiante != null && estudiante.getContrasena().equals(contrasena);
    }
}