package com.fidelitas.service;

import com.fidelitas.domain.Estudiante;

import java.util.ArrayList;
import java.util.List;

public interface EstudianteService {

    public List<Estudiante> getEstudiantes();

    public Estudiante getEstudianteByCorreo(String correo);

    ArrayList<Estudiante> findAll();
    public List<Estudiante> findActive();
    public List<Estudiante> findInactive();
    public void save(Estudiante estudiante);

    Estudiante findById(Long id);

    void edit(Estudiante estudiante);

    void changeStatus(Estudiante estudiante);

    public Estudiante getEstudianteFromUserDetails(Object userDetails);
}