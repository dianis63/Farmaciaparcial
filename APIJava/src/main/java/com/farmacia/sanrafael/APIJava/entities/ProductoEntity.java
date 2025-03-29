package com.farmacia.sanrafael.APIJava.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class ProductoEntity {
    @Id
    @Column(columnDefinition = "INT", name="id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(columnDefinition = "VARCHAR(100)")
    private String nombre;

    @Column(columnDefinition = "VARCHAR(200)")
    private String descripcion;

    @Column(columnDefinition = "DOUBLE")
    private Double precio;

    @Column(columnDefinition = "INT")
    private Integer stock;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date fecha_vencimiento;



}
