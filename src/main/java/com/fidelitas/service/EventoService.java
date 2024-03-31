package com.fidelitas.service;

import com.fidelitas.domain.Evento;
import java.util.List;

public interface EventoService {
    
    public List<Evento> getEventos();
    
    public Evento getEvento(Evento evento);
    
    public void deleteEvento(Evento evento);
    
    public void saveEvento(Evento evento); 
}
