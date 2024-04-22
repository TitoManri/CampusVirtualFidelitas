package com.fidelitas.controller;

import com.fidelitas.dao.EstudianteDao;
import com.fidelitas.domain.Estudiante;
import com.fidelitas.domain.Tarjeta;
import com.fidelitas.domain.TipoTarjeta;
import com.fidelitas.domain.ZonaPagos;
import com.fidelitas.service.ZonaPagosService;
import com.fidelitas.service.impl.ZonaPagosServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class ZonaPagosController {

    @Autowired
    private ZonaPagosService zonaPagosService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private EstudianteDao estudianteDao;

    @GetMapping("/zonaPagos")
    public String mostrarPaginaPagare(Model model) {
        Estudiante estudiante = (Estudiante) httpSession.getAttribute("estudiante");
        if (estudiante != null) {
            model.addAttribute("estudiante", estudiante);
        }


        if (httpSession.getAttribute("successMessage") != null) {
            model.addAttribute("successMessage", httpSession.getAttribute("successMessage"));
            httpSession.removeAttribute("successMessage");
        }

        if (httpSession.getAttribute("errorMessage") != null) {
            model.addAttribute("errorMessage", httpSession.getAttribute("errorMessage"));
            httpSession.removeAttribute("errorMessage");
        }

        return "zonaPagos";
    }

    @PostMapping("/zonaPagos/formularioPago")
    public String procesarFormulario(@RequestParam("banco") String banco,
                                     @RequestParam("numeroTarjeta") String numeroTarjeta,
                                     @RequestParam("fechaVencimiento") String fechaVencimiento,
                                     @RequestParam("tipoTarjeta") String tipoTarjeta,
                                     @RequestParam("pin") String pin,
                                     @RequestParam("metodoPago") String metodoPago,
                                     Model model) {

        Estudiante estudianteOnSession = (Estudiante) httpSession.getAttribute("estudiante");

        Estudiante estudiante = estudianteDao.findByCorreo(estudianteOnSession.getCorreo());

        // no se ha implementado la logica de las tarjetas y metodos de pago TODO
        if (zonaPagosService.realizarPago(banco, numeroTarjeta, fechaVencimiento, tipoTarjeta, pin, metodoPago, estudiante)) {
            httpSession.setAttribute("successMessage", "Pago realizado con éxito");
        } else {
            httpSession.setAttribute("errorMessage", "No se pudo realizar el pago");
        }

        // Por último, redirige a la vista que mostrará la confirmación o cualquier otra acción que necesites
        return "redirect:/zonaPagos";
    }
}
