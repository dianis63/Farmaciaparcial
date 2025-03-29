package com.farmacia.sanrafael.APIJava.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaEntity {
    @Id
    @Column(columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_venta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME", nullable = false)
    private Date fecha = new Date();

    @Column(columnDefinition = "DOUBLE", nullable = false)
    private Double total;

    @Column(columnDefinition = "INT", nullable = false)
    private Integer id_cliente;

    @Column(columnDefinition = "INT", nullable = false)
    private Integer id_empleado;

    @Column(columnDefinition = "VARCHAR(1)", length = 1, nullable = false)
    private String estado;
}
