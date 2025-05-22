package com.farmacia.sanrafael.APIJava.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "El id del producto no puede ser nulo.")
    private Long id_producto;

    @Column(columnDefinition = "INT", nullable = false)
    @NotNull(message = "La cantidad no puede ser nula.")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0.")
    private Integer cantidad;

    @Column(columnDefinition = "DOUBLE", nullable = false)
    @NotNull(message = "El precio de compra no puede ser nulo.")
    @Min(value = 0, message = "El precio de compra debe ser positivo.")
    private Double precio_compra;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATE", nullable = false)
    private Date fecha_ingreso = new Date();

    @Column(columnDefinition = "INT", nullable = false)
    @NotNull(message = "El id del empleado no puede ser nulo.")
    private Long id_empleado;
}
