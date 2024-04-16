package com.fidelitas.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "admin", schema = "fidelitas", catalog = "")
public class Admin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_admin")
    private int idAdmin;
    @Basic
    @Column(name = "correo")
    private String correo;
    @Basic
    @Column(name = "contrasena")
    private String contrasena;
}

