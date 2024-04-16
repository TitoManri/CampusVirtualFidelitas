package com.fidelitas.controller;


import com.fidelitas.domain.Admin;
import com.fidelitas.domain.Estudiante;
import com.fidelitas.service.InicioSesionService;
import java.util.List;

import jakarta.servlet.http.HttpSession;
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
    @Autowired
    private HttpSession httpSession;

    @RequestMapping({"/", "/login"})
    public String mostrarPaginaInicioSesion(Model model) {
        model.addAttribute("inicioSesion", new Estudiante());
        return "inicio";
    }

    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String contrasena, Model model) {
        String posibleTipoDeUsuario = inicioSesionService.obtenerElPosibleTipoDeUsuario(correo);
        if (posibleTipoDeUsuario == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return "login";
        }

        if (posibleTipoDeUsuario == "estudiante") {
            Estudiante estudiante = inicioSesionService.loggearEstudiante(correo, contrasena);

            if (estudiante == null) {
                model.addAttribute("error", "Credenciales incorrectas");
                return "login";
            }

            // en todas las paginas donde se necesite saber si el estudiante esta loggeado se puede obtener desde la sesion
            httpSession.setAttribute("estudianteLoggeado", estudiante);
            return "redirect:/paginaprincipal";
        } else if (posibleTipoDeUsuario == "admin") {
            // loggear al admin
            Admin admin = inicioSesionService.loggearAdmin(correo, contrasena);

            if (admin == null) {
                model.addAttribute("error", "Credenciales incorrectas");
                return "login";
            }
            // en todas las paginas donde se necesite saber si el admin esta loggeado se puede obtener desde la sesion
            httpSession.setAttribute("adminLoggeado", admin);
            return "redirect:/admin/administrar-estudiantes";
        } else {
            model.addAttribute("error", "Usuario no encontrado");
            return "login";
        }
    }
    
}



