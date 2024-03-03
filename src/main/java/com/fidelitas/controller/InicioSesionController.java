package com.fidelitas.controller;


import com.fidelitas.domain.InicioSesion;
import com.fidelitas.service.InicioSesionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class InicioSesionController {
    
    private final InicioSesionService iniciosesionService;

    @Autowired
    public InicioSesionController(InicioSesionService inicioSesionService) {
        this.iniciosesionService = inicioSesionService;
    }

    @GetMapping("/iniciosesion")
    public String mostrarPaginaInicioSesion(Model model) {
        List<InicioSesion> estudiantes = iniciosesionService.getInicioSesiones(true);
        model.addAttribute("estudiantes", estudiantes);

        return "iniciosesion/index";
    }
}
