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
    
     // Método para manejar la solicitud POST del formulario de edición de notificación
    @PostMapping("/editar-notificacion")
    public String editarNotificacion(@RequestParam("id") Long id,@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion) {
        // Busca la notificación por su ID
        Notificaciones notificaciones = notificacionesDao.findById(id).orElse(null);
        
        // Verifica si la notificación existe y actualiza sus campos
        if (notificaciones != null) {
            notificaciones.setNombre(nombre);
            notificaciones.setDescripcion(descripcion);
            // Actualiza la notificación en la base de datos
            notificacionesDao.save(notificaciones);
        }
        
        // Redirige de nuevo a la página actual
        return "redirect:/notificaciones";
    }
    // Método para eliminar una notificación
        @PostMapping("/eliminar-notificacion")
        public String eliminarNotificacion(@RequestParam("id") Long id) {
            notificacionesDao.deleteById(id);
            return "redirect:/notificaciones"; // Redirigir a la página de notificaciones después de eliminar
        }

    
}
