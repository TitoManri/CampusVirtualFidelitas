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
    public String mostrarApartadoClases(Model model) {
        List<ApartadoClases> cursos = apartadoClasesDao.findAll(); //Lista para cursos
        List<Personal> profesores = personalDao.findAll(); //Lista poara profesores
        model.addAttribute("cursos", cursos); 
        model.addAttribute("curso", new ApartadoClases()); // Crea un nuevo objeto para leer todos los datos de la base de datos y los guarda en un list
        model.addAttribute("profesores", profesores); // Crea un nuevo objeto para leer todos los datos de la base de datos y los guarda en un list
        return "/admin/apartadoClases";
    }

   

    @GetMapping("/clases/{id}")
    public String verClase(@PathVariable Long id, Model model) {
        ApartadoClases clase = apartadoClasesDao.findById(id).orElse(null);
        if (clase != null) { //Si se encuantra el objeto con la informacion de la base de datos pasa sino da error
            model.addAttribute("clase", clase); //Saca la informacion de clase o se de los cursos
            model.addAttribute("nombreCurso", clase.getNombreCurso()); //Obtiene el nombre del curso
            model.addAttribute("descripcionCurso", clase.getDescripcion()); //Obtiene la descripcion del curso
            
            return "introduccion";
        } else {
            return "error";
        }
    }

    
    @GetMapping("/clases/{id}/semana/{numeroSemana}")
    public String verSemanas(@PathVariable Long id, @PathVariable int numeroSemana, Model model) {
        ApartadoClases clase = apartadoClasesDao.findById(id).get(); //Obtiene el id del curso
        model.addAttribute("clase", clase); //Obtiene la informacion de la clase
        String nombreCurso = clase.getNombreCurso(); //Obtiene el nombre del curso 
        model.addAttribute("nombreCurso", nombreCurso); //Obtiene el nombre del curso para usarlo con thymeleaf
        model.addAttribute("numeroSemana", numeroSemana); //Obtiene el numero de la semana (de 1 a 15)
        return "semana";
    }




    @PostMapping("/admin/apartadoClases/agregar")
    @PreAuthorize("hasRole('ADMIN')")//Este metodo solo lo pueden accesar los administradores
    public String agregarApartadoClase(@ModelAttribute ApartadoClases apartadoClase, @RequestParam("id_personal") Long idPersonal) {
        Personal profesor = personalDao.findById(idPersonal).orElse(null); //Se encuenta el id del profesor o sino se pone en null
        if (profesor != null) {  //Si se encuantra el objeto con la informacion de la base de datos pasa sino da error
            apartadoClase.setProfesor(profesor); //Obtiene el id del profesor
            apartadoClasesDao.save(apartadoClase); //Se guarda la clase
            Long id = apartadoClase.getId(); //Se obtiene el id del curso
            String url = "/clases/" + id; //Se genera una URL automaticamente para cada curso creado
            apartadoClase.setUrl(url); //Se guarda el url en la base de datos
            apartadoClasesDao.save(apartadoClase); //Se guarda otra vez el curso
            return "redirect:/admin/apartadoClases";
        } else {
            return "error";
        }
    }



}
