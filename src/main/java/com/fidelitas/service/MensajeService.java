package com.fidelitas.service;

import com.fidelitas.domain.Mensaje;
import jakarta.mail.MessagingException;
import java.util.List;

public interface MensajeService {

    public List<Mensaje> getMensajes();

    public Mensaje getMensaje(Long id);

    public void enviarMensaje(String to, String subject, String body) throws MessagingException;

    public void saveMensaje(String subject, String body, String from, String to);
}
