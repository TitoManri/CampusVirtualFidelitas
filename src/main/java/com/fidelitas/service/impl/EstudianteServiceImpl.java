package com.fidelitas.service.impl;

import com.fidelitas.dao.EstudianteDao;
import com.fidelitas.domain.Estudiante;
import com.fidelitas.service.EstudianteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    
    private final EstudianteDao estudianteDao;
    

    @Autowired
    public EstudianteServiceImpl(EstudianteDao estudianteDao) {
        this.estudianteDao = estudianteDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Estudiante> getEstudiantes() {
        var lista = estudianteDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Estudiante getEstudianteByCorreo(String correo) {
        return estudianteDao.findByCorreo(correo);
    }
}