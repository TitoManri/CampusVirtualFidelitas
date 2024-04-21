package com.fidelitas.controller;

import com.fidelitas.domain.Evento;
import com.fidelitas.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CalendarioController {

    @Autowired
    private EventoService eventoService;

    //VISUALIZAR EVENTOS
    @GetMapping("/calendario")
    public String mostrarListado(Model model) {
        var eventos = eventoService.getEventos(); 
        model.addAttribute("eventos", eventos); //Obtiene eventos de la base de datos
        return "calendario"; 
    }

    //CRUD
    @PostMapping("/guardar")
    public String guardar(Evento evento) {
        eventoService.saveEvento(evento); //Se guarda el evento en la base de datos
        return "redirect:/calendario";
    }

    @GetMapping("/eliminar/{idEvento}")
    public String elimina(Evento evento) {
        eventoService.deleteEvento(evento); //Se elimina el evento de la base de datos
        return "redirect:/calendario";
    }

    @GetMapping("/modificar/{idEvento}")
    public String modifica(Evento evento, Model model) {
        evento = eventoService.getEvento(evento);
        model.addAttribute("evento", evento); //Obtiene el evento de la base de daos y lo modifica
        return "/modificaEvento";
    }

}
