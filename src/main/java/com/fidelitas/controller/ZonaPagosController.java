package com.fidelitas.controller;

import com.fidelitas.service.ZonaPagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
