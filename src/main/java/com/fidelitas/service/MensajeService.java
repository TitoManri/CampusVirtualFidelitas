package com.fidelitas.service;

import com.fidelitas.domain.Mensaje;
import java.util.List;

public interface MensajeService {

    public List<Mensaje> getMensajes();

    public Mensaje getMensaje(Long id);

    public void enviarMensaje(String from, String to, String subject, String body);

    public void saveMensaje(Mensaje mensaje);
}
