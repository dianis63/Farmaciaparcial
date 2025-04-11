package com.farmacia.sanrafael.APIJava.entities;
import jakarta.persistence.*;
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
    private Long id_venta;

    @Column(columnDefinition = "INT", name = "id_producto", nullable = false)
    private Long id_producto;

    @Column(columnDefinition = "INT", nullable = false)
    private Long cantidad;

    @Column(columnDefinition = "DOUBLE", nullable = false)
    private Double precio_unitario;
}
