package com.fidelitas.service;

import com.fidelitas.domain.*;
import com.fidelitas.dao.*;
import java.util.List;

public interface ApartadoClasesService {
    public List<ApartadoClases> getApartadoclases(boolean activos);

    public ApartadoClases getApartadoClases(ApartadoClases apartadoClases);
    
    public void guardarCurso(ApartadoClases apartadoClases);
    
    public void borrarCurso(ApartadoClases apartadoClases);
}