package com.fidelitas.service.impl;

import com.fidelitas.dao.MetodoPagoDao;
import com.fidelitas.domain.MetodoPago;
import com.fidelitas.service.MetodoPagoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetodoPagoServiceImpl implements MetodoPagoService{
    
    @Autowired
    private MetodoPagoDao metodoPagoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<MetodoPago> getMetodoPagos() {
        var lista = metodoPagoDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public MetodoPago getMetodoPagoById(long id) {
        return metodoPagoDao.findById(id);
    }
}
