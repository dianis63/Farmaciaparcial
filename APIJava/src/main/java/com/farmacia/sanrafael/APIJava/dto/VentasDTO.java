package com.farmacia.sanrafael.APIJava.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentasDTO {


    private Long id_venta;
    private Date fecha;
    private Double total;
    //Para mostrar
    private String nombre_cliente;
    private String nombre_empleado;
    // Para guardar
    private Long id_cliente;
    private Long id_empleado;
    private String estado;
}
