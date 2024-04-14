package com.fidelitas.controller;


import com.fidelitas.dao.ApartadoClasesDao;
import com.fidelitas.dao.PersonalDao;
import com.fidelitas.domain.ApartadoClases;
import com.fidelitas.domain.Personal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "paginaprincipal";
    }

}
