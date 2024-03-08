package com.fidelitas.service.impl;

import com.fidelitas.dao.InicioSesionDao;
import com.fidelitas.domain.InicioSesion;
import com.fidelitas.service.InicioSesionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InicioSesionServiceImpl implements InicioSesionService {

    @Autowired
    private InicioSesionDao inicioSesionDao;

    @Override
    public boolean verificarCredenciales(String correo, String contrasena) {
        // Buscar el inicio de sesión por correo
        InicioSesion inicioSesion = inicioSesionDao.findByCorreo(correo);

        // Verificar si el inicio de sesión existe y si la contraseña coincide
        return inicioSesion != null && inicioSesion.getContrasena().equals(contrasena);
    }
}