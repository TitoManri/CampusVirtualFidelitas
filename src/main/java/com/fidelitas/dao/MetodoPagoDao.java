package com.fidelitas.dao;

import com.fidelitas.domain.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetodoPagoDao extends JpaRepository <MetodoPago, Long> {
    MetodoPago findById(long id);

    MetodoPago findByNombre(String metodoPago);
}
