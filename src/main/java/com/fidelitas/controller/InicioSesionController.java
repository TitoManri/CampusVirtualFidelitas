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

    private final InicioSesionService inicioSesionService;

    @Autowired
    public InicioSesionController(InicioSesionService inicioSesionService) {
        this.inicioSesionService = inicioSesionService;
    }

    @GetMapping("/iniciosesion")
    public String mostrarPaginaInicioSesion(Model model) {
        List<InicioSesion> estudiantes = inicioSesionService.getInicioSesiones(true);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("inicioSesion", new InicioSesion()); // Agrega esto

        return "iniciosesion/index";
    }


    @PostMapping("/login")
    public String procesarInicioSesion(@ModelAttribute InicioSesion inicioSesion, Model model) {
        // Lógica para procesar el inicio de sesión y operaciones en la base de datos
        // Puedes llamar a métodos de inicioSesionService aquí para manejar la base de datos

        // Después de procesar el inicio de sesión, podrías redirigir a otra página o simplemente volver a mostrar la misma página
        return "redirect:/dashboard";  // Redirige a otra página después de procesar el inicio de sesión
    }
}
