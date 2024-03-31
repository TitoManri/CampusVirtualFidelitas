package com.fidelitas.controller;


import com.fidelitas.dao.ApartadoClasesDao;
import com.fidelitas.domain.ApartadoClases;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaginaPrincipalController {

    @Autowired
    private ApartadoClasesDao apartadoClasesDao;
    
    @GetMapping("/paginaprincipal")
    public String mostrarPaginaPrincipal(Model model) {
        List<ApartadoClases> cursos = apartadoClasesDao.findAll();
        model.addAttribute("cursos", cursos);
        model.addAttribute("curso", new ApartadoClases());
        return "paginaprincipal";
    }
}
