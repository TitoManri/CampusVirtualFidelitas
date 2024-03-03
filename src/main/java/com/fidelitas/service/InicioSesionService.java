package com.fidelitas.service;

import com.fidelitas.domain.InicioSesion;
import java.util.List;

public interface InicioSesionService {
    
    // Se obtiene un listado de categorias en un List
    public List<InicioSesion> getInicioSesiones(boolean activos);
    
   // Se obtiene un Categoria, a partir del id de un categoria
    public InicioSesion getInicioSesion(InicioSesion iniciosesion);
    
    // Se inserta un nuevo categoria si el id del categoria esta vacío
    // Se actualiza un categoria si el id del categoria NO esta vacío
    public void save(InicioSesion iniciosesion);
    
    // Se elimina el categoria que tiene el id pasado por parámetro
    public void delete(InicioSesion iniciosesion);
}
