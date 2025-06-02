package com.farmacia.sanrafael.APIJava.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVentaDTO {
    private Long id_detalle;
    private Long id_venta;
    private Long id_producto;
    private String nombre_producto;
    private Long cantidad;
    private Double precio_unitario;
}
