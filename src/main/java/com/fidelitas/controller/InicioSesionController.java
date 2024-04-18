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

    // Muestra la página de inicio de sesión
    @RequestMapping({"/", "/login"})
    public String mostrarPaginaInicioSesion(Model model) {
        // Agrega un objeto Estudiante al modelo para el formulario de inicio de sesión
        model.addAttribute("inicioSesion", new Estudiante());
        return "inicio";
    }

    // Maneja el proceso de inicio de sesión
    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String contrasena, Model model) {
        // Obtiene el posible tipo de usuario (estudiante o admin) basado en el correo
        String posibleTipoDeUsuario = inicioSesionService.obtenerElPosibleTipoDeUsuario(correo);

        // Si no se encuentra el usuario, muestra un mensaje de error y redirige a la página de inicio de sesión
        if (posibleTipoDeUsuario == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return "login";
        }

        // Si el usuario es un estudiante
        if (posibleTipoDeUsuario.equals("estudiante")) {
            // Intenta hacer login como estudiante
            Estudiante estudiante = inicioSesionService.loggearEstudiante(correo, contrasena);

            // Si las credenciales son incorrectas, muestra un mensaje de error y redirige a la página de inicio de sesión
            if (estudiante == null) {
                model.addAttribute("error", "Credenciales incorrectas");
                return "login";
            }

            // Almacena el estudiante loggeado en la sesión y redirige a la página principal
            httpSession.setAttribute("estudianteLoggeado", estudiante);
            return "redirect:/paginaprincipal";
        } // Si el usuario es un administrador
        else if (posibleTipoDeUsuario.equals("admin")) {
            // Intenta hacer login como admin
            Admin admin = inicioSesionService.loggearAdmin(correo, contrasena);

            // Si las credenciales son incorrectas, muestra un mensaje de error y redirige a la página de inicio de sesión
            if (admin == null) {
                model.addAttribute("error", "Credenciales incorrectas");
                return "login";
            }
            // Almacena el admin loggeado en la sesión y redirige a la página de administración de estudiantes
            httpSession.setAttribute("adminLoggeado", admin);
            return "redirect:/admin/administrar-estudiantes";
        } // Si el tipo de usuario no es reconocido, muestra un mensaje de error y redirige a la página de inicio de sesión
        else {
            model.addAttribute("error", "Usuario no encontrado");
            return "login";
        }
    }

}
