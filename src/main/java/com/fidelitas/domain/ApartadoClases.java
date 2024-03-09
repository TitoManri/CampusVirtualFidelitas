package com.fidelitas.domain;

import com.fidelitas.dao.*;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity 
@Table(name = "apartadoClases")

public class ApartadoClases implements Serializable {
    
    @Id  // Para el Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private String idCurso;
    private String nombreCurso;
    private String profesorCurso;
    private String horario;
    private boolean activo;
    
    }
