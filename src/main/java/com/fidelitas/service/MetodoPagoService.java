package com.fidelitas.service;

import com.fidelitas.domain.MetodoPago;
import java.util.List;

/**
 *
 * @author Nigel
 */
public interface MetodoPagoService {

    public List<MetodoPago> getMetodoPagos();

    public MetodoPago getMetodoPagoById(long id);
}
