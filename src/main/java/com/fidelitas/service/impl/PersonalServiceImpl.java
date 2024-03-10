package com.fidelitas.service.impl;

import com.fidelitas.dao.PersonalDao;
import com.fidelitas.domain.Personal;
import com.fidelitas.service.PersonalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalServiceImpl implements PersonalService {
    
    private final PersonalDao personalDao;
    

    @Autowired
    public PersonalServiceImpl(PersonalDao personalDao) {
        this.personalDao = personalDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personal> getPersonal() {
        var lista = personalDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Personal getPersonalByCorreo(String correo) {
        return personalDao.findByCorreo(correo);
    }

}