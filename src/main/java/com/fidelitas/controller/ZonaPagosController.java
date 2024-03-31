package com.fidelitas.controller;

import com.fidelitas.domain.Estudiante;
import com.fidelitas.service.EstudianteService;
import com.fidelitas.service.TarjetaService;
import com.fidelitas.service.ZonaPagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ZonaPagosController {

    @Autowired
    private ZonaPagosService zonaPagosService;
    
    @RequestMapping("/zonaPagos")
    public String mostrarPaginaPagare() {
        return "zonaPagos";
    }

    @PostMapping("/formularioPago")
    public String mostrarFormulario(Model model) {
        return "listados";
    }

}
