/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.controller;

import com.fidelitas.dao.ApartadoClasesDao;
import com.fidelitas.dao.PersonalDao;
import com.fidelitas.domain.ApartadoClases;
import com.fidelitas.domain.Personal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApartadoClasesController {

    @Autowired
    private ApartadoClasesDao apartadoClasesDao;
    
    @Autowired
    private PersonalDao personalDao;

    @GetMapping("/admin/apartadoClases")
    @PreAuthorize("hasRole('ADMIN')")
    public String mostrarApartadoClases(Model model) {
        List<ApartadoClases> cursos = apartadoClasesDao.findAll();
        List<Personal> profesores = personalDao.findAll();
        model.addAttribute("cursos", cursos);
        model.addAttribute("curso", new ApartadoClases());
        model.addAttribute("profesores", profesores); 
        return "/admin/apartadoClases";
    }

   

    @GetMapping("/clases/{id}")
    public String verClase(@PathVariable Long id, Model model) {
        ApartadoClases clase = apartadoClasesDao.findById(id).orElse(null);
        if (clase != null) {
            model.addAttribute("clase", clase);
            model.addAttribute("nombreCurso", clase.getNombreCurso());
            model.addAttribute("descripcionCurso", clase.getDescripcion());
            return "introduccion";
        } else {
            return "error";
        }
    }

    
    @GetMapping("/clases/{id}/semana/{numeroSemana}")
    public String verSemanas(@PathVariable Long id, @PathVariable int numeroSemana, Model model) {
        ApartadoClases clase = apartadoClasesDao.findById(id).get();
        model.addAttribute("clase", clase);
        String nombreCurso = clase.getNombreCurso();
        model.addAttribute("nombreCurso", nombreCurso);
        model.addAttribute("numeroSemana", numeroSemana);
        return "semana";
    }




    @PostMapping("/admin/apartadoClases/agregar")
    @PreAuthorize("hasRole('ADMIN')")
    public String agregarApartadoClase(@ModelAttribute ApartadoClases apartadoClase, @RequestParam("id_personal") Long idPersonal) {
        Personal profesor = personalDao.findById(idPersonal).orElse(null);
        if (profesor != null) {
            apartadoClase.setProfesor(profesor);
            apartadoClasesDao.save(apartadoClase);
            Long id = apartadoClase.getId();
            String url = "/clases/" + id;
            apartadoClase.setUrl(url); 
            apartadoClasesDao.save(apartadoClase);
            return "redirect:/admin/apartadoClases";
        } else {
            return "error";
        }
    }



}
