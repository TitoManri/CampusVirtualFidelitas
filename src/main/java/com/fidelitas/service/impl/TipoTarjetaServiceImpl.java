package com.fidelitas.service.impl;

import com.fidelitas.dao.TipoTarjetaDao;
import com.fidelitas.domain.TipoTarjeta;
import com.fidelitas.service.TipoTarjetaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoTarjetaServiceImpl implements TipoTarjetaService {
    @Autowired
    private TipoTarjetaDao tipoTarjetaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<TipoTarjeta> getTipoTarjetas() {
        var lista = tipoTarjetaDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public TipoTarjeta getTipoTarjetaById(long id) {
        return tipoTarjetaDao.findById(id);
    }
}
