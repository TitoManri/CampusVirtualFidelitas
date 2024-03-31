/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.controller;

import com.fidelitas.dao.ApartadoClasesDao;
import com.fidelitas.domain.ApartadoClases;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApartadoClasesController {

    @Autowired
    private ApartadoClasesDao apartadoClasesDao;

    @GetMapping("/apartadoClases")
    public String mostrarApartadoClases(Model model) {
        List<ApartadoClases> cursos = apartadoClasesDao.findAll();
        model.addAttribute("cursos", cursos);
        model.addAttribute("curso", new ApartadoClases());
        return "apartadoClases";
    }
   

    @PostMapping("/apartadoClases/agregar")
    public String agregarApartadoClase(@ModelAttribute ApartadoClases apartadoClase) {
        apartadoClasesDao.save(apartadoClase);
        return "redirect:/apartadoClases";
    }
}
