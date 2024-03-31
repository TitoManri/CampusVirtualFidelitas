package com.fidelitas.dao;

import com.fidelitas.domain.Estudiante;
import com.fidelitas.domain.Tarjeta;
import com.fidelitas.domain.ZonaPagos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZonaPagosDao extends JpaRepository<ZonaPagos, Long> {

    ZonaPagos findById(long id);
}
