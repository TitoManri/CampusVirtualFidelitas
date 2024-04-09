package com.fidelitas.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;


@Data
@Entity
@Table(name = "curso")
public class ApartadoClases implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long id;

    @Column(name = "nombre_curso")
    private String nombreCurso;

    @ManyToOne
    @JoinColumn(name = "id_personal") 
    private Personal profesor;

    private String horario;

    @Column(name = "fecha_inicio")
    private String fechaInicio;

    @Column(name = "fecha_fin")
    private String fechaFin;

    private Boolean activo;
    private String descripcion;
    private String url;
    
    @Column(name = "url_imagen")
    private String urlImagen;
    
}
