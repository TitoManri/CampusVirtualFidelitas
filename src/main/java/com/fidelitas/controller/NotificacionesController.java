package com.fidelitas.controller;

import com.fidelitas.dao.NotificacionesDao;
import com.fidelitas.dao.PersonalDao;
import com.fidelitas.domain.Notificaciones;
import com.fidelitas.domain.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificacionesController {
    
    @Autowired
    private NotificacionesDao notificacionesDao;
    
    @Autowired
    private PersonalDao personalDao;
    
    @GetMapping("/notificaciones")
    public String mostrarNotificaciones(Model model) {
        List<Notificaciones> notificaciones = notificacionesDao.findAll(); //Se obtiene la tabla notificaciones por medio de la lista
        List<Personal> profesores = personalDao.findAll(); //Se obtiene la tabla personal por medio de la lista
        model.addAttribute("notificaciones", notificaciones);
        model.addAttribute("notificacion", new Notificaciones()); //Se crea un nuevo objeto notificaciones
        model.addAttribute("profesores", profesores); //Se utiliza para profesores en la base de datos para usarlo en el html
        return "notificaciones";
    }
    
    @PostMapping("/notificaciones/agregar")
    public String agregarNotificacion(Notificaciones notificacion, @RequestParam("id_personal") Long idPersonal) {
        Personal profesor = personalDao.findById(idPersonal).orElse(null); //Obtiene el id del personal 
        if (profesor != null) { //Si no se encuentra dentro del objetp el profesor da error 
            notificacion.setProfesor(profesor); 
            notificacionesDao.save(notificacion); //Se guarda la notificaion
            return "redirect:/notificaciones";
        } else {
            return "error";
        }
    }
}
