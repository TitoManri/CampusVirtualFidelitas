package com.fidelitas.controller;


import com.fidelitas.dao.ApartadoClasesDao;
import com.fidelitas.dao.PersonalDao;
import com.fidelitas.domain.ApartadoClases;
import com.fidelitas.domain.Personal;
import java.util.List;

import com.fidelitas.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaPrincipalController {

    @Autowired
    private ApartadoClasesDao apartadoClasesDao;
    
      @Autowired
    private PersonalDao personalDao;
    
    @GetMapping("/paginaprincipal")
    public String mostrarPaginaPrincipal(Model model) {
        List<ApartadoClases> cursos = apartadoClasesDao.findAll();
        List<Personal> profesores = personalDao.findAll();
        model.addAttribute("cursos", cursos);
        model.addAttribute("curso", new ApartadoClases());
        model.addAttribute("profesor", profesores); 
        model.addAttribute("profesores", new Personal());

        // Obtener el objeto UserDetails
        var userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails != null) {
            if (userDetails.toString().contains("ROLE_ADMIN")) {
                return "redirect:/admin/administrar-estudiantes";
            } else if (userDetails.toString().contains("ROLE_USER")) {
                return "paginaprincipal";
            }
        }

        return "redirect:/login";
    }

}
