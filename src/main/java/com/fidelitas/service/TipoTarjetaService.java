package com.fidelitas.service;

import com.fidelitas.domain.TipoTarjeta;
import java.util.List;

public interface TipoTarjetaService {

    public List<TipoTarjeta> getTipoTarjetas();

    public TipoTarjeta getTipoTarjetaById(long id);
}
