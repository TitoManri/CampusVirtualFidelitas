package com.fidelitas.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;


@Data
@Entity
@Table(name = "notificaciones")
public class Notificaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificaciones")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_personal") 
    private Personal profesor;

    private String descripcion;
    private String fecha;
    

    
}
