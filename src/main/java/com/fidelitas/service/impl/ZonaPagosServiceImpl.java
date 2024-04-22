package com.fidelitas.service.impl;

import com.fidelitas.dao.MetodoPagoDao;
import com.fidelitas.dao.TarjetaDao;
import com.fidelitas.dao.TipoTarjetaDao;
import com.fidelitas.dao.ZonaPagosDao;
import com.fidelitas.domain.*;
import com.fidelitas.service.ZonaPagosService;

import java.time.LocalDateTime;
import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZonaPagosServiceImpl implements ZonaPagosService {

    @Autowired
    private ZonaPagosDao zonaPagosDao;
    @Autowired
    private TipoTarjetaDao tipoTarjetaDao;
    @Autowired
    private MetodoPagoDao metodoPagoDao;
    @Autowired
    private TarjetaDao tarjetaDao;

    @Override
    @Transactional(readOnly = true)
    public List<ZonaPagos> getZonaPagos() {
        return zonaPagosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ZonaPagos getZonaPagosById(long id) {
        return zonaPagosDao.findById(id);
    }

    @Override
    @Transactional
    public void savePago(ZonaPagos pago) {
        zonaPagosDao.save(pago);
    }

    @Override
    public boolean realizarPago(String banco, String numeroTarjeta, String fechaVencimiento, String tipoTarjeta, String pin, String metodoPago, Estudiante estudiante) {
        System.out.println("realizarPago starting");
        TipoTarjeta tipoTarjetaDaoByNombre;

        try {
            tipoTarjetaDaoByNombre = tipoTarjetaDao.findByNombre(tipoTarjeta);
            System.out.println("Tipo tarjeta encontrado: " + tipoTarjetaDaoByNombre);
        } catch (Exception e) {
            // esto se deberia cambiar por un mensaje de error o devolver false, pero como aun no esta implementado
            System.out.println("Tipo tarjeta no encontrado");
            // si no existe el tipo de tarjeta se crea uno nuevo
            tipoTarjetaDaoByNombre = new TipoTarjeta();
            tipoTarjetaDaoByNombre.setNombre(tipoTarjeta);
            tipoTarjetaDao.save(tipoTarjetaDaoByNombre);
        }

        MetodoPago metodoPagoDaoByNombre;

        try {
            metodoPagoDaoByNombre = metodoPagoDao.findByNombre(metodoPago);
            System.out.println("Metodo pago encontrado: " + metodoPagoDaoByNombre);
        } catch (Exception e) {
            // esto se deberia cambiar por un mensaje de error o devolver false, pero como aun no esta implementado
            System.out.println("Metodo pago no encontrado");
            // si no existe el metodo de pago se crea uno nuevo
            metodoPagoDaoByNombre = new MetodoPago();
            metodoPagoDaoByNombre.setNombre(metodoPago);
            metodoPagoDao.save(metodoPagoDaoByNombre);
        }


        Tarjeta tarjetaDaoByNumeroTarjeta;

        try {
            tarjetaDaoByNumeroTarjeta = tarjetaDao.findByNumero(numeroTarjeta);
            System.out.println("Tarjeta encontrada: " + tarjetaDaoByNumeroTarjeta);
        } catch (Exception e) {
            // esto se deberia cambiar por un mensaje de error o devolver false, pero como aun no esta implementado
            System.out.println("Tarjeta no encontrada");
            // si no existe la tarjeta se crea una nueva
            tarjetaDaoByNumeroTarjeta = new Tarjeta();
            tarjetaDaoByNumeroTarjeta.setNumero(numeroTarjeta);
            tarjetaDaoByNumeroTarjeta.setFechaVencimiento(fechaVencimiento);
            tarjetaDaoByNumeroTarjeta.setPin(Integer.parseInt(pin));
            tarjetaDaoByNumeroTarjeta.setIdTipoTarjeta(tipoTarjetaDaoByNombre);
            tarjetaDao.save(tarjetaDaoByNumeroTarjeta);
        }

        if (tarjetaDaoByNumeroTarjeta.getPin() != Integer.parseInt(pin)) {
            System.out.println("Pin incorrecto");
            return false;
        }

        ZonaPagos pago = new ZonaPagos();
        pago.setBanco(banco);
        pago.setDescripcion("Pago por curso");
        pago.setFecha(LocalDateTime.now());
        pago.setEstadoPago(true);  

        pago.setMonto(100.00);  // cuando se implemente el precio de los cursos se debe cambiar
        pago.setIdTarjeta(tarjetaDaoByNumeroTarjeta);

        // cambiar cuando se implemente la logica de los estudiantes
        pago.setIdEstudiante(estudiante);

        zonaPagosDao.save(pago);

        return true;
    }
}
