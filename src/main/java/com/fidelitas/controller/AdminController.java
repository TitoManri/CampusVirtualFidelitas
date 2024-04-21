package com.fidelitas.controller;

import com.fidelitas.domain.Estudiante;
import com.fidelitas.service.impl.EstudianteServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"listaDeEstudiantes"})
public class AdminController {

    // Método para inicializar la lista de estudiantes en la sesión
    @ModelAttribute("listaDeEstudiantes")
    public ArrayList<Estudiante> listaDeEstudiantes() {
        return new ArrayList<>();
    }

    // Servicios necesarios
    private final EstudianteServiceImpl estudianteService;
    private final HttpSession httpSession;

    // Constructor que inyecta los servicios y la sesión HTTP
    public AdminController(EstudianteServiceImpl estudianteService, HttpSession httpSession) {
        this.estudianteService = estudianteService;
        this.httpSession = httpSession;
    }

    // Método para mostrar la página de administración de estudiantes
    @GetMapping("/admin/administrar-estudiantes")
    public String getEstudiantes(Model model, @RequestParam(value = "filtroEstudiantes", required = false) String filtroEstudiantes) {
        // Lógica para filtrar estudiantes activos o inactivos
        if (filtroEstudiantes == null || filtroEstudiantes.isEmpty() || filtroEstudiantes.equals("activos")) {
            List<Estudiante> estudiantesActivos = estudianteService.findActive();
            model.addAttribute("listaDeEstudiantes", estudiantesActivos);
            model.addAttribute("filtroEstudiantes", "activos");
        } else if (filtroEstudiantes.equals("inactivos")) {
            List<Estudiante> estudiantesInactivos = estudianteService.findInactive();
            model.addAttribute("listaDeEstudiantes", estudiantesInactivos);
            model.addAttribute("filtroEstudiantes", "inactivos");
        }

        return "admin/dashboard";
    }

    // Método para mostrar el formulario de agregar estudiante
    @GetMapping("/admin/agregar-estudiante")
    public String agregarEstudiante(Model model) {
        model.addAttribute("estudiante", new Estudiante());

        // Manejo de mensajes de éxito o error
        if (httpSession.getAttribute("successMessage") != null) {
            model.addAttribute("successMessage", httpSession.getAttribute("successMessage"));
            httpSession.removeAttribute("successMessage");
        }

        if (httpSession.getAttribute("errorMessage") != null) {
            model.addAttribute("errorMessage", httpSession.getAttribute("errorMessage"));
            httpSession.removeAttribute("errorMessage");
        }

        return "/admin/agregar-estudiante";
    }

    // Método para procesar el formulario de agregar estudiante
    @PostMapping("/admin/agregar-estudiante")
    public String agregarEstudiante(@ModelAttribute Estudiante estudiante, Model model) {

        // Intenta guardar al estudiante
        try {
            estudianteService.save(estudiante);
            httpSession.setAttribute("successMessage", "Estudiante agregado correctamente");
        } catch (Exception e) {
            httpSession.setAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/agregar-estudiante";
    }

    // Método para ver detalles de un estudiante
    @GetMapping("/admin/estudiantes/{id}")
    public String verEstudiante(@PathVariable("id") Long id, Model model) {
        // Obtiene el estudiante por su ID
        Estudiante estudiante = estudianteService.findById(id);
        if (estudiante == null) {
            httpSession.setAttribute("errorMessage", "Estudiante no encontrado");
            return "redirect:/admin/administrar-estudiantes";
        }

        model.addAttribute("estudiante", estudiante);

        // Manejo de mensajes de éxito o error
        if (httpSession.getAttribute("successMessage") != null) {
            model.addAttribute("successMessage", httpSession.getAttribute("successMessage"));
            httpSession.removeAttribute("successMessage");
        }

        if (httpSession.getAttribute("errorMessage") != null) {
            model.addAttribute("errorMessage", httpSession.getAttribute("errorMessage"));
            httpSession.removeAttribute("errorMessage");
        }

        return "admin/ver-estudiante";
    }

    // Método para mostrar el formulario de editar estudiante
    @GetMapping("/admin/editar-estudiante/{id}")
    public String editarEstudiante(@PathVariable("id") Long id, Model model) {

        // Obtiene el estudiante por su ID
        Estudiante estudiante = estudianteService.findById(id);
        if (estudiante == null) {
            httpSession.setAttribute("errorMessage", "Estudiante no encontrado");
            return "redirect:/admin/administrar-estudiantes";
        }

        model.addAttribute("estudiante", estudiante);

        // Manejo de mensajes de éxito o error
        if (httpSession.getAttribute("successMessage") != null) {
            model.addAttribute("successMessage", httpSession.getAttribute("successMessage"));
            httpSession.removeAttribute("successMessage");
        }

        if (httpSession.getAttribute("errorMessage") != null) {
            model.addAttribute("errorMessage", httpSession.getAttribute("errorMessage"));
            httpSession.removeAttribute("errorMessage");
        }

        return "/admin/editarEstudiante";
    }

    // Método para procesar el formulario de editar estudiante
    @PostMapping("/admin/editar-estudiante/{id}")
    public String editarEstudiante(@PathVariable("id") Long id, @ModelAttribute Estudiante estudiante, Model model) {


        estudiante.setIdEstudiante(id);

        // Intenta editar al estudiante
        try {
            estudianteService.edit(estudiante);
            httpSession.setAttribute("successMessage", "Estudiante editado correctamente");
        } catch (Exception e) {
            httpSession.setAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/estudiantes/" + id;
    }

    // Método para cambiar el estado de un estudiante (activo/inactivo)
    @GetMapping("/admin/cambiarEstado/{id}")
    public String cambiarEstado(@PathVariable("id") Long id, Model model) {

        // Obtiene el estudiante por su ID
        Estudiante estudiante = estudianteService.findById(id);
        if (estudiante == null) {
            httpSession.setAttribute("errorMessage", "Estudiante no encontrado");
            return "redirect:/admin/administrar-estudiantes";
        }

        // Intenta cambiar el estado del estudiante
        try {
            estudianteService.changeStatus(estudiante);
            httpSession.setAttribute("successMessage", "Estado del estudiante cambiado correctamente");
        } catch (Exception e) {
            httpSession.setAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/estudiantes/" + id;
    }
}
