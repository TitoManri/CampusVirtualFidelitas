package com.fidelitas.controller;

import com.fidelitas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fidelitas.domain.TipoUsuario;

@Controller
@RequestMapping("/mensaje")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.getUsuariosByTipo(TipoUsuario.Estudiante); 
        model.addAttribute("usuarios", usuarios); 
        return "/mensaje/listado";
    }

}