package com.fidelitas.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "mensaje")
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private long idMensaje;

    private String asunto;
    private String contenido;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    private boolean leido;

    @ManyToOne
    @JoinColumn(name = "id_emisor", referencedColumnName = "id_usuario")
    private Usuario emisor;

    @ManyToOne
    @JoinColumn(name = "id_receptor", referencedColumnName = "id_usuario")
    private Usuario receptor;

}