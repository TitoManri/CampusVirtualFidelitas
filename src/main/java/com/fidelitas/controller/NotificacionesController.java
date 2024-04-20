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
        List<Notificaciones> notificaciones = notificacionesDao.findAll();
        List<Personal> profesores = personalDao.findAll();
        model.addAttribute("notificaciones", notificaciones);
        model.addAttribute("notificacion", new Notificaciones());
        model.addAttribute("profesores", profesores); 
        return "notificaciones";
    }
    
    @PostMapping("/notificaciones/agregar")
    public String agregarNotificacion(Notificaciones notificacion, @RequestParam("id_personal") Long idPersonal) {
        Personal profesor = personalDao.findById(idPersonal).orElse(null);
        if (profesor != null) {
            notificacion.setProfesor(profesor);
            notificacionesDao.save(notificacion);
            return "redirect:/notificaciones";
        } else {
            return "error";
        }
    }
}
