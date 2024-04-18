package com.fidelitas.service;

import com.fidelitas.domain.MetodoPago;
import java.util.List;

public interface MetodoPagoService {

    public List<MetodoPago> getMetodoPagos();

    public MetodoPago getMetodoPagoById(long id);
}
