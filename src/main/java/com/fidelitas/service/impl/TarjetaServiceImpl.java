package com.fidelitas.service.impl;

import com.fidelitas.dao.TarjetaDao;
import com.fidelitas.domain.Tarjeta;
import com.fidelitas.service.TarjetaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaDao tarjetaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Tarjeta> getTarjetas() {
        var lista = tarjetaDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Tarjeta getTarjetaById(long id) {
        Optional<Tarjeta> tarjeta = tarjetaDao.findById(id);
        return tarjeta.orElse(null);
    }

    @Override
    public boolean validarTarjeta(Tarjeta tarjeta) {
        String fechaVencimientoString = tarjeta.getFechaVencimiento();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        // Convertir la fecha de vencimiento de String a LocalDate
        LocalDate fechaVencimiento = LocalDate.parse(fechaVencimientoString, formatter);
        LocalDate fechaActual = LocalDate.now();

        // Verificar si la fecha de vencimiento no se ha cumplido aún
        if (fechaVencimiento.isBefore(fechaActual)) {
            return false; // La tarjeta está vencida
        }

        String numeroTarjeta = tarjeta.getNumero();
        if (numeroTarjeta.length() != 16) {
            return false; 
        }
        
        return true;
    }
}
