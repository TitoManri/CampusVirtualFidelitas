package com.fidelitas.dao;
import com.fidelitas.domain.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionesDao extends JpaRepository<Notificaciones, Long> {
    
}
