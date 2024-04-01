package com.fidelitas.service;

//import com.fidelitas.domain.Estudiante;
import com.fidelitas.domain.Tarjeta;
import java.util.List;

/**
 *
 * @author Nigel
 */
public interface TarjetaService {

    public List<Tarjeta> getTarjetas();

    public Tarjeta getTarjetaById(long id);

//    public List<Tarjeta> getTarjetaByEstudiante(Estudiante estudiante);

    public boolean validarTarjeta(Tarjeta tarjeta);

}
