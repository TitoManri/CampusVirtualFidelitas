package com.fidelitas.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "personal")
public class Personal implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private long idPersonal;
    
    private String cargo;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasena;
    private String fotoPerfil;
    private boolean estado;
}
