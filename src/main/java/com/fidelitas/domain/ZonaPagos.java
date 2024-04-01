package com.fidelitas.domain;

import com.fidelitas.dao.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "pago")
public class ZonaPagos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private long idPago;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante idEstudiante;

    @ManyToOne
    @JoinColumn(name = "id_tarjeta")
    private Tarjeta idTarjeta;

    private String banco;
    private double monto;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    private boolean estadoPago;
}
