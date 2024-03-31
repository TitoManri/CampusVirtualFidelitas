package com.fidelitas.service;

import com.fidelitas.domain.*;
import java.util.List;

/**
 *
 * @author Nigel Ocampo
 */
public interface ZonaPagosService {

    public List<ZonaPagos> getZonaPagos();

    public ZonaPagos getZonaPagosById(long id);

    public void savePago(ZonaPagos pago);
    
//    public void enviarVerificacion(Estudiante estudiante);
}
