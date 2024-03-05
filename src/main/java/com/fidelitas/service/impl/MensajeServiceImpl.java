/*
package com.fidelitas.service.impl;

import com.fidelitas.dao.MensajeDao;
import com.fidelitas.domain.Mensaje;
import com.fidelitas.service.MensajeService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeDao mensajeDao;

    @Override
    @Transactional(readOnly = true)
    public List<Mensaje > getMensajes() {
        return mensajeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mensaje getMensaje(Long id) {
        return mensajeDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveMensaje(Mensaje mensaje) {
        mensajeDao.save(mensaje);
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void enviarMensaje(String to, String from, String subject, String body) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(body, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
*/