package com.fidelitas.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long idUsuario;
    
    private String nombre;
    private String apellidos;
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    private boolean graduado;
    private boolean matriculado;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_administrador")
    private CargoAdministrador cargoAdministrador;

}

