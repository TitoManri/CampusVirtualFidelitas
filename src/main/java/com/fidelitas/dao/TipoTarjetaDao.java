package com.fidelitas.dao;

import com.fidelitas.domain.TipoTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nigel
 */
public interface TipoTarjetaDao extends JpaRepository <TipoTarjeta, Long>{
    TipoTarjeta findById(long id);
}
