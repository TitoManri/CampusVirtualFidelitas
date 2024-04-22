package com.fidelitas.service.impl;

import com.fidelitas.dao.EstudianteDao;
import com.fidelitas.domain.Estudiante;
import com.fidelitas.service.EstudianteService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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

    @Override
    @Transactional(readOnly = true)
    public ArrayList<Estudiante> findAll() {
        return (ArrayList<Estudiante>) estudianteDao.findAll();
    }

    @Override
    public List<Estudiante> findActive() {
        return estudianteDao.findAllByEstado(true);
    }

    @Override
    public List<Estudiante> findInactive() {
        return estudianteDao.findAllByEstado(false);
    }

    @Override
    public void save(Estudiante estudiante) {
        try {
            String BCryptPassword = new BCryptPasswordEncoder().encode(estudiante.getContrasena());
            estudiante.setContrasena(BCryptPassword);

            estudiante.setRol("ESTUDIANTE");
            estudianteDao.save(estudiante);
        } catch (Exception e) {
            if (e.getMessage().contains("ConstraintViolationException")) {
                throw new RuntimeException("El correo ya está registrado");
            } else {
                throw new RuntimeException("Error al guardar el estudiante");
            }
        }
    }

    @Override
    public Estudiante findById(Long id) {
        return estudianteDao.findById(id).orElse(null);
    }

    @Override
    public void edit(Estudiante estudiante) {
        Estudiante estudianteActual = estudianteDao.findById(estudiante.getIdEstudiante()).orElse(null);
        if (estudianteActual == null) {
            throw new RuntimeException("Error al editar el estudiante: El estudiante no existe");
        }

        Estudiante estudianteByCorreo = estudianteDao.findByCorreo(estudiante.getCorreo());

        if (estudianteByCorreo != null && estudiante.getIdEstudiante() != estudianteByCorreo.getIdEstudiante()) {
            throw new RuntimeException("Error al editar el estudiante: El correo ya está registrado");
        }

        estudianteActual.setNombre(estudiante.getNombre());
        estudianteActual.setApellidos(estudiante.getApellidos());
        estudianteActual.setCorreo(estudiante.getCorreo());
        estudianteActual.setFotoPerfil(estudiante.getFotoPerfil());

        String BCryptPassword = new BCryptPasswordEncoder().encode(estudiante.getContrasena());
        estudianteActual.setContrasena(BCryptPassword);
        
        estudianteDao.save(estudianteActual);
    }

    @Override
    public void changeStatus(Estudiante estudiante) {
        Estudiante estudianteActual = estudianteDao.findById(estudiante.getIdEstudiante()).orElse(null);
        if (estudianteActual == null) {
            throw new RuntimeException("Error al cambiar el estado del estudiante: El estudiante no existe");
        }

        // cambiar el estado del estudiante 
        estudianteActual.setEstado(!estudiante.isEstado());
        estudianteDao.save(estudianteActual);
    }

    @Override
    public Estudiante getEstudianteFromUserDetails(Object userDetails) {
        try {
            String correo = trimCorreo(userDetails.toString());
            return estudianteDao.findByCorreo(correo);
        } catch (Exception e) {
            return null;
        }
    }

    private String trimCorreo(String text) {
        // Buscar el índice de inicio del correo electrónico
        int startIndex = text.indexOf("Username=") + "Username=".length();
        // Buscar el índice de fin del correo electrónico
        int endIndex = text.indexOf(",", startIndex);
        // Extraer el correo electrónico
        return text.substring(startIndex, endIndex);
    }
}