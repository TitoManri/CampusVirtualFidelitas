package com.fidelitas.service.impl;

import com.fidelitas.service.ApartadoClasesService;
import com.fidelitas.domain.ApartadoClases;
import com.fidelitas.dao.ApartadoClasesDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApartadoClasesServiceImpl implements ApartadoClasesService{
    
    @Autowired
    private ApartadoClasesDao apartadoClasesDao;
    
    @Transactional(readOnly = true)
    public List<ApartadoClases> getApartadoClasess(boolean activos) {
        var lista = apartadoClasesDao.findAll();
        if (activos) {
            lista.removeIf(c -> !c.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional
    public void guardarCurso(ApartadoClases apartadoClases) {
        apartadoClasesDao.save(apartadoClases);
    }

    @Override
    @Transactional
    public void borrarCurso(ApartadoClases apartadoClases) {
        apartadoClasesDao.delete(apartadoClases);
    }

    @Override
    public List<ApartadoClases> getApartadoclases(boolean activos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ApartadoClases getApartadoClases(ApartadoClases apartadoClases) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
