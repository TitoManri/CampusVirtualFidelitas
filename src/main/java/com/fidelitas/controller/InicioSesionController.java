package com.fidelitas.controller;


import com.fidelitas.domain.Estudiante;
import com.fidelitas.service.InicioSesionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InicioSesionController {

    @Autowired
    
    private InicioSesionService inicioSesionService;

    @RequestMapping({"/", "/login"})
    public String mostrarPaginaInicioSesion(Model model) {
        model.addAttribute("inicioSesion", new Estudiante());
        return "inicio";
    }

    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String contrasena, Model model) {
        // Verificar las credenciales del inicio de sesión usando el servicio
        boolean credencialesValidas = inicioSesionService.verificarCredenciales(correo, contrasena);

        if (credencialesValidas) {
            // Inicio de sesión autenticado, redirigir a la página principal
            return "redirect:/paginaprincipal";
        } else {
            // Credenciales inválidas, mostrar un mensaje de error en la vista
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }
    
}



