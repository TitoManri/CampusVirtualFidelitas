package com.fidelitas.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaginaPrincipalController {

    @RequestMapping("/paginaprincipal")
    public String mostrarPaginaPrincipal() {
        return "paginaprincipal"; 
    }
}
