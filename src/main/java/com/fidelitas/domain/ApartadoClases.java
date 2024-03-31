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

    @Column(name = "id_personal")
    private Long idPersonal;

    private String horario;

    @Column(name = "fecha_inicio")
    private String fechaInicio;

    @Column(name = "fecha_fin")
    private String fechaFin;

    private Boolean activo;
}
