package com.fidelitas.dao;

import com.fidelitas.domain.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaDao extends JpaRepository <Tarjeta, Long> {
    Tarjeta findByIdTarjeta(long id);

    Tarjeta findByNumero(String numeroTarjeta);
}
