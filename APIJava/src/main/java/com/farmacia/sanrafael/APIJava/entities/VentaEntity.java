package com.farmacia.sanrafael.APIJava.entities;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

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
    private Long id_venta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME", nullable = false)
    private Date fecha = new Date();

    @Column(columnDefinition = "DOUBLE", nullable = false)
    private Double total;

    @Column(columnDefinition = "INT", nullable = false)
    @NotNull(message = "El campo 'id_cliente' no puede ser nulo.")
    private Long id_cliente;

    @Column(columnDefinition = "INT", nullable = false)
    @NotNull(message = "El campo 'id_empleado' no puede ser nulo.")
    private Long id_empleado;

    @NotNull(message = "El estado no puede ser nulo.")
    @Column(columnDefinition = "VARCHAR(1)", length = 1, nullable = false)
    private String estado;
}
