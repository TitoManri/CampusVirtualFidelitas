/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.controller;

import com.fidelitas.service.ApartadoClasesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ans
 */

@Controller
@Slf4j
@RequestMapping("/apartadoClases")
public class ApartadoClasesController {
    
    @Autowired
    private ApartadoClasesService apartadoClasesService;
    
    
}
