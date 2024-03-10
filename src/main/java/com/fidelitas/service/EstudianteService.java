package com.fidelitas.service;

import com.fidelitas.domain.Estudiante;
import java.util.List;

public interface EstudianteService {

    public List<Estudiante> getEstudiantes();

    public Estudiante getEstudianteByCorreo(String correo);
}
