package com.fidelitas.service.impl;

import com.fidelitas.dao.MensajeDao;
import com.fidelitas.domain.Mensaje;
import com.fidelitas.service.EstudianteService;
import com.fidelitas.service.MensajeService;
import com.fidelitas.service.PersonalService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;
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
    
    @Autowired
    private EstudianteService estudianteService;
    
    @Autowired
    private PersonalService personalService;

    @Override
    @Transactional(readOnly = true)
    public List<Mensaje> getMensajes() {
        return mensajeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mensaje getMensaje(Long id) {
        return mensajeDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveMensaje(String subject, String body, String from, String to) {
        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto(subject);
        mensaje.setFechaEnvio(LocalDateTime.now());
        mensaje.setContenido(body);
        mensaje.setLeido(false);
        mensaje.setEmisor(estudianteService.getEstudianteByCorreo(from));
        mensaje.setReceptor(personalService.getPersonalByCorreo(to));
        mensajeDao.save(mensaje);
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void enviarMensaje(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);

        javaMailSender.send(message);
    }

}
