package com.fidelitas.controller;

import com.fidelitas.domain.Estudiante;
import com.fidelitas.service.ZonaPagosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ZonaPagosController {

    @Autowired
    private ZonaPagosService zonaPagosService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/zonaPagos")
    public String mostrarPaginaPagare(Model model) {
        Estudiante estudiante = (Estudiante) httpSession.getAttribute("estudiante");
        if (estudiante != null) {
            model.addAttribute("estudiante", estudiante);
        }
        return "zonaPagos";
    }

    @PostMapping("/formularioPago")
    public String mostrarFormulario(Model model) {
        return "listados";
    }

}
