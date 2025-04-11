package com.farmacia.sanrafael.APIJava.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "ingreso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngresoEntity {
    @Id
    @Column(columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ingreso;

    @Column(columnDefinition = "INT", nullable = false)
    private Long id_producto;

    @Column(columnDefinition = "INT", nullable = false)
    private Integer cantidad;

    @Column(columnDefinition = "DOUBLE", nullable = false)
    private Double precio_compra;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATE", nullable = false)
    private Date fecha_ingreso = new Date();

    @Column(columnDefinition = "INT", nullable = false)
    private Long id_empleado;
}
