package com.fidelitas.service;

import com.fidelitas.domain.*;
import java.util.List;

public interface ZonaPagosService {

    public List<ZonaPagos> getZonaPagos();

    public ZonaPagos getZonaPagosById(long id);

    public void savePago(ZonaPagos pago);

    public boolean realizarPago(String banco, String numeroTarjeta, String fechaVencimiento, String tipoTarjeta, String pin, String metodoPago, Estudiante estudiante);

//    public void enviarVerificacion(Estudiante estudiante);
}
