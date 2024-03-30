package com.fidelitas.dao;

import com.fidelitas.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoDao extends JpaRepository <Evento, Long>{ 

}
