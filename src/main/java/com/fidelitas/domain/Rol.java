/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="rol")
public class Rol implements Serializable{
    
    private static final Long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_rol")
    private Long idRol;
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    public void setNombre(String role_user) {
        this.nombre = role_user;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public Rol() {
    }
    
    public Rol(Long idRol, String nombre, Usuario usuario) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.usuario = usuario;
    }
    
    public Rol(String nombre, Usuario usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
    }
}
