package com.farmacia.sanrafael.APIJava.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Entity
@Table(name = "Detalleventa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaEntity {
    @Id
    @Column(columnDefinition = "INT", name = "id_detalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;

    @Column(columnDefinition = "INT", name = "id_venta", nullable = false)
    @NotNull(message = "El id de la venta no puede ser nulo.")
    private Long id_venta;

    @Column(columnDefinition = "INT", name = "id_producto", nullable = false)
    @NotNull(message = "El id del producto no puede ser nulo.")
    private Long id_producto;

    @Column(columnDefinition = "INT", nullable = false)
    @NotNull(message = "La cantidad no puede ser nula.")
    @Min(value = 1, message = "La cantidad debe ser al menos 1.")
    private Long cantidad;

    @Column(columnDefinition = "DOUBLE", nullable = false)
    private Double precio_unitario;
}
