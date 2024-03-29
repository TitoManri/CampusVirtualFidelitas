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
@RequestMapping("/mensaje")
public class MensajeController {

    @Autowired
    private PersonalService personalService;

    
    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = personalService.getPersonal(); 
        model.addAttribute("usuarios", usuarios); 
        return "/mensaje/listado";
    }
    
    @Autowired
    private MensajeService mensajeService;

    // Endpoint para enviar correo
    @PostMapping("/enviarCorreo")
    public String enviarCorreo(@RequestParam String to, @RequestParam String subject, @RequestParam String body, @RequestParam String from) throws MessagingException {
        mensajeService.saveMensaje(subject, body, from, to);
        mensajeService.enviarMensaje(to, subject, body);
        return "redirect:/mensaje/listado";
    }

}
