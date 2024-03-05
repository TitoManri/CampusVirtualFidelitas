package com.fidelitas.controller;

import com.fidelitas.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    //Endpoint para enviar correo
    @PostMapping("/mensaje/enviarCorreo")
    @ResponseBody
    public void enviarCorreo(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body
    ) {
        mensajeService.enviarMensaje(from, to, subject, body);
    }

}
