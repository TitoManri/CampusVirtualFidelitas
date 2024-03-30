package com.fidelitas.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private long idEvento;

    private String nombreEvento;
    private LocalDate fechaEvento;
    
    @Column(name = "descripcion_evento")
    private String descripcionEvento;
}
