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
    private Integer id_venta;

    @Column(columnDefinition = "INT", name = "id_producto", nullable = false)
    private Integer id_producto;

    @Column(columnDefinition = "INT", nullable = false)
    private Integer cantidad;

    @Column(columnDefinition = "DOUBLE", nullable = false)
    private Double precio_unitario;
}
