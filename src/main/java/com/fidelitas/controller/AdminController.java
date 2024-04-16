package com.fidelitas.controller;

import com.fidelitas.domain.Admin;
import com.fidelitas.domain.Estudiante;
import com.fidelitas.service.impl.AdminServiceImpl;
import com.fidelitas.service.impl.EstudianteServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"listaDeEstudiantes"})
public class AdminController {
    @ModelAttribute("listaDeEstudiantes") public ArrayList<Estudiante> listaDeEstudiantes() { return new ArrayList<>(); }


    private final AdminServiceImpl adminService;
    private final EstudianteServiceImpl estudianteService;  // para editar a los estudiantes
    private final HttpSession httpSession;

    public AdminController(AdminServiceImpl adminService, EstudianteServiceImpl estudianteService, HttpSession httpSession) {
        this.adminService = adminService;
        this.estudianteService = estudianteService;
        this.httpSession = httpSession;
    }

    @GetMapping("/admin/administrar-estudiantes")
    public String getEstudiantes(Model model, @RequestParam(value = "filtroEstudiantes", required = false) String filtroEstudiantes) {
        Admin adminLoggedo = (Admin) httpSession.getAttribute("adminLoggeado");
        if (adminLoggedo == null) {
            return "redirect:/login";
        }

        // Si el filtro es null o está vacío, mostrar todos los estudiantes activos por defecto
        if (filtroEstudiantes == null || filtroEstudiantes.isEmpty() || filtroEstudiantes.equals("activos")) {
            // obtener la lista de estudiantes activos y agregarla al model
            List<Estudiante> estudiantesActivos = estudianteService.findActive();
            model.addAttribute("listaDeEstudiantes", estudiantesActivos);
            model.addAttribute("filtroEstudiantes", "activos");
        } else if (filtroEstudiantes.equals("inactivos")) {
            // obtener la lista de estudiantes inactivos y agregarla al model
            List<Estudiante> estudiantesInactivos = estudianteService.findInactive();
            model.addAttribute("listaDeEstudiantes", estudiantesInactivos);
            model.addAttribute("filtroEstudiantes", "inactivos");
        }

        return "admin/dashboard";
    }

    @GetMapping("/admin/agregar-estudiante")
    public String agregarEstudiante(Model model) {
        Admin adminLoggedo = (Admin) httpSession.getAttribute("adminLoggeado");
        if (adminLoggedo == null) {
            return "redirect:/login";
        }

        model.addAttribute("estudiante", new Estudiante());

        if (httpSession.getAttribute("successMessage") != null) {
            model.addAttribute("successMessage", httpSession.getAttribute("successMessage"));
            httpSession.removeAttribute("successMessage");
        }

        if (httpSession.getAttribute("errorMessage") != null) {
            model.addAttribute("errorMessage", httpSession.getAttribute("errorMessage"));
            httpSession.removeAttribute("errorMessage");
        }

        return "admin/agregar-estudiante";
    }

    @PostMapping("/admin/agregar-estudiante")
    public String agregarEstudiante(@ModelAttribute Estudiante estudiante, Model model) {
        Admin adminLoggedo = (Admin) httpSession.getAttribute("adminLoggeado");
        if (adminLoggedo == null) {
            return "redirect:/login";
        }

        try {
            estudianteService.save(estudiante);
            httpSession.setAttribute("successMessage", "Estudiante agregado correctamente");
        } catch (Exception e) {
            httpSession.setAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/agregar-estudiante";
    }

    ///admin/estudiantes/' + ${estudiante.getIdEstudiante()}
    @GetMapping("/admin/estudiantes/{id}")
    public String verEstudiante(@PathVariable("id") Long id, Model model) {
        Admin adminLoggedo = (Admin) httpSession.getAttribute("adminLoggeado");
        if (adminLoggedo == null) {
            return "redirect:/login";
        }

        Estudiante estudiante = estudianteService.findById(id);
        if (estudiante == null) {
            httpSession.setAttribute("errorMessage", "Estudiante no encontrado");
            return "redirect:/admin/administrar-estudiantes";
        }

        model.addAttribute("estudiante", estudiante);

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

//                    <a th:href="@{/admin/editar-estudiante/{idEstudiante}(idEstudiante=${estudiante.idEstudiante})}"></a>
    @GetMapping("/admin/editar-estudiante/{id}")
    public String editarEstudiante(@PathVariable("id") Long id, Model model) {
        Admin adminLoggedo = (Admin) httpSession.getAttribute("adminLoggeado");
        if (adminLoggedo == null) {
            return "redirect:/login";
        }

        Estudiante estudiante = estudianteService.findById(id);
        if (estudiante == null) {
            httpSession.setAttribute("errorMessage", "Estudiante no encontrado");
            return "redirect:/admin/administrar-estudiantes";
        }

        model.addAttribute("estudiante", estudiante);

        if (httpSession.getAttribute("successMessage") != null) {
            model.addAttribute("successMessage", httpSession.getAttribute("successMessage"));
            httpSession.removeAttribute("successMessage");
        }

        if (httpSession.getAttribute("errorMessage") != null) {
            model.addAttribute("errorMessage", httpSession.getAttribute("errorMessage"));
            httpSession.removeAttribute("errorMessage");
        }

        return "admin/editarEstudiante";
    }

    @PostMapping("/admin/editar-estudiante/{id}")
    public String editarEstudiante(@PathVariable("id") Long id, @ModelAttribute Estudiante estudiante, Model model) {
        Admin adminLoggedo = (Admin) httpSession.getAttribute("adminLoggeado");
        if (adminLoggedo == null) {
            return "redirect:/login";
        }

        estudiante.setIdEstudiante(id);

        try {
            estudianteService.edit(estudiante);
            httpSession.setAttribute("successMessage", "Estudiante editado correctamente");
        } catch (Exception e) {
            httpSession.setAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/estudiantes/" + id;
    }

    @GetMapping("/admin/cambiarEstado/{id}")
    public String cambiarEstado(@PathVariable("id") Long id, Model model) {
        Admin adminLoggedo = (Admin) httpSession.getAttribute("adminLoggeado");
        if (adminLoggedo == null) {
            return "redirect:/login";
        }

        Estudiante estudiante = estudianteService.findById(id);
        if (estudiante == null) {
            httpSession.setAttribute("errorMessage", "Estudiante no encontrado");
            return "redirect:/admin/administrar-estudiantes";
        }

        try {
            estudianteService.changeStatus(estudiante);
            httpSession.setAttribute("successMessage", "Estado del estudiante cambiado correctamente");
        } catch (Exception e) {
            httpSession.setAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/estudiantes/" + id;
    }
}
