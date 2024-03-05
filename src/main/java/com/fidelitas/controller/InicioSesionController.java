package com.fidelitas.controller;


import com.fidelitas.domain.InicioSesion;
import com.fidelitas.service.InicioSesionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InicioSesionController {
    
    @Autowired
    private InicioSesionService inicioSesionService;
    
    @GetMapping("/")
    public String mostrarPaginaInicioSesion(Model model) {
        model.addAttribute("inicioSesion", new InicioSesion());
        return "inicio";
    }

}

