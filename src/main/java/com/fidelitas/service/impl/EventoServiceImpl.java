package com.fidelitas.service.impl;

import com.fidelitas.dao.EventoDao;
import com.fidelitas.domain.Evento;
import com.fidelitas.service.EventoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoServiceImpl implements EventoService{
    
    private final EventoDao eventoDao;
    
    @Autowired
    public EventoServiceImpl(EventoDao eventoDao){
        this.eventoDao = eventoDao;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Evento> getEventos() {
        var lista = eventoDao.findAll();
        return lista;
    }

    @Override
    public Evento getEvento(Evento evento) {
        return eventoDao.findById(evento.getIdEvento()).orElse(null);
    }

    @Override
    public void deleteEvento(Evento evento) {
        eventoDao.delete(evento);
    }

    @Override
    public void saveEvento(Evento evento) {
        eventoDao.save(evento);
    }

}
