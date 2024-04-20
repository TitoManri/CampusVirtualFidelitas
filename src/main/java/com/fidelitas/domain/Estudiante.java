package com.fidelitas.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "estudiante")
public class Estudiante implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private long idEstudiante;
    
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasena;
    private String fotoPerfil;
    private boolean estado;
    private String rol;
}

