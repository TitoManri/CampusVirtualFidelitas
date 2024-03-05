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
    @Transactional(readOnly = true)
    public List<InicioSesion> getInicioSesiones(boolean activos) {
        var lista = inicioSesionDao.findAll();
        if (activos) {
            lista.removeIf(c -> !c.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public InicioSesion getInicioSesion(InicioSesion inicioSesion) {
        return inicioSesionDao.findById(inicioSesion.getIdEstudiante()).orElse(null);
    }

    @Override
    @Transactional
    public void save(InicioSesion inicioSesion) {
        inicioSesionDao.save(inicioSesion);
    }

    @Override
    @Transactional
    public void delete(InicioSesion inicioSesion) {
        inicioSesionDao.delete(inicioSesion);
    }
    
    @Override
    public InicioSesion buscarPorCorreo(String correo) {
        return inicioSesionDao.findByCorreo(correo);
    }
    
}