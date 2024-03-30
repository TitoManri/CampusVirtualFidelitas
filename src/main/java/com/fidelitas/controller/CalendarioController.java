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
@RequestMapping("/calendario")
public class CalendarioController {

    @Autowired
    private EventoService eventoService;

    //VISUALIZAR EVENTOS
    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        var eventos = eventoService.getEventos();
        model.addAttribute("eventos", eventos);
        return "/calendario/listado"; 
    }

    //CRUD
    @PostMapping("/guardar")
    public String guardar(Evento evento) {
        eventoService.saveEvento(evento);
        return "redirect:/calendario/listado";
    }

    @GetMapping("/eliminar/{idEvento}")
    public String elimina(Evento evento) {
        eventoService.deleteEvento(evento);
        return "redirect:/calendario/listado";
    }

    @GetMapping("/modificar/{idEvento}")
    public String modifica(Evento evento, Model model) {
        evento = eventoService.getEvento(evento);
        model.addAttribute("evento", evento);
        return "/evento/modifica";
    }

}
