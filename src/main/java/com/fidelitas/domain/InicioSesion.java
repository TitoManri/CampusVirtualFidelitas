/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Manrique Carazo
 */
@Data
@Entity // Para decir que es una tabla de la base de datos
@Table(name = "estudiante")

public class InicioSesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id  // Para el Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Long idEstudiante;
    private String nombre;
    private String correo;
    private String contrasena;
    private String rutaImagen;
    private boolean activo;

}
