package com.fidelitas.service.impl;

import com.fidelitas.dao.ZonaPagosDao;
import com.fidelitas.domain.Estudiante;
import com.fidelitas.domain.Tarjeta;
import com.fidelitas.domain.ZonaPagos;
import com.fidelitas.service.ZonaPagosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZonaPagosServiceImpl implements ZonaPagosService {

    @Autowired
    private ZonaPagosDao zonaPagosDao;

    @Override
    @Transactional(readOnly = true)
    public List<ZonaPagos> getZonaPagos() {
        return zonaPagosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ZonaPagos getZonaPagosById(long id) {
        return zonaPagosDao.findById(id);
    }

    @Override
    @Transactional
    public void savePago(ZonaPagos pago) {
        zonaPagosDao.save(pago);
    }
    
//    @Override
//    @Transactional
//    public void enviarVerificacion(Estudiante estudiante){
//        
//    }        
}
