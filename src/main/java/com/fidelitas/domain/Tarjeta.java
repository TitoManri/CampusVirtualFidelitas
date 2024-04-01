package com.fidelitas.domain;

/**
 *
 * @author Nigel
 */
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "tarjeta")
public class Tarjeta implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarjeta")
    private long idTarjeta;

    @ManyToOne
    @JoinColumn(name = "id_tipo_tarjeta")
    private Estudiante idTipoTarjeta;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private Estudiante idMetodoPago;

    private String fechaVencimiento;
    private int pin;
    private String numero;
}
