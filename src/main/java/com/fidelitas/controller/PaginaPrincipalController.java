package com.fidelitas.controller;


import com.fidelitas.dao.ApartadoClasesDao;
import com.fidelitas.dao.PersonalDao;
import com.fidelitas.domain.ApartadoClases;
import com.fidelitas.domain.Estudiante;
import com.fidelitas.domain.Personal;
import java.util.List;

import com.fidelitas.service.EstudianteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class PaginaPrincipalController {

    @Autowired
    private ApartadoClasesDao apartadoClasesDao;
    
    @Autowired
    private PersonalDao personalDao;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/paginaprincipal")
    public String mostrarPaginaPrincipal(Model model) {
        List<ApartadoClases> cursos = apartadoClasesDao.findAll();//Lista para cursos 
        List<Personal> profesores = personalDao.findAll(); //Lista para profesores
        model.addAttribute("cursos", cursos);
        model.addAttribute("curso", new ApartadoClases());// Crea un nuevo objeto para leer todos los datos de la base de datos y los guarda en un list
        model.addAttribute("profesor", profesores); 
        model.addAttribute("profesores", new Personal()); // Crea un nuevo objeto para leer todos los datos de la base de datos y los guarda en un list

        // Obtener el objeto UserDetails
        var userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails != null) {
            if (userDetails.toString().contains("ROLE_USER") || userDetails.toString().contains("ROLE_ADMIN")) {
                // obtener el usuario autenticado
                Estudiante estudiante = estudianteService.getEstudianteFromUserDetails(userDetails);
                if (estudiante != null) {
                    httpSession.setAttribute("estudiante", estudiante);
                }
                return "paginaprincipal";
            }
        }

        return "redirect:/login";
    }
    
    @GetMapping("/cambiarIdioma")
    public String cambiarIdioma(@RequestParam("lang") String lang, HttpServletRequest request, HttpServletResponse response) {
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


}
