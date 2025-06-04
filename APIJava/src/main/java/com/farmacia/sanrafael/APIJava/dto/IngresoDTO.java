package com.farmacia.sanrafael.APIJava.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngresoDTO {
    private Long id_ingreso;
    private Long id_producto;
    private String nombre_producto;
    private Integer cantidad;
    private Double precio_compra;
    private Date fecha_ingreso;
    private Long id_empleado;
    private String nombre_empleado;
}
