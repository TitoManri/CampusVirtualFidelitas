package com.fidelitas.controller;

import com.fidelitas.service.MensajeService;
import com.fidelitas.service.PersonalService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MensajeController {

    @Autowired
    private PersonalService personalService;

    
    @GetMapping("/mensajeria")
    public String listado(Model model) {
        var usuarios = personalService.getPersonal(); 
        model.addAttribute("usuarios", usuarios);  //Obtiene el personal de la base de datos
        return "mensajeria";
    }
    
    @Autowired
    private MensajeService mensajeService;

    // Endpoint para enviar correo
    @PostMapping("/enviarCorreo")
    public String enviarCorreo(@RequestParam String to, @RequestParam String subject, @RequestParam String body, @RequestParam String from) throws MessagingException {
        mensajeService.saveMensaje(subject, body, from, to); //Guarda el mensaje en la base de datos
        mensajeService.enviarMensaje(to, subject, body); //Se utiliza para enviar el correo
        return "redirect:mensajeria";
    }

}
