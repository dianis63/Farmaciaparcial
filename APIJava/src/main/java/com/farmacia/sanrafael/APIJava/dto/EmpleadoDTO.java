package com.farmacia.sanrafael.APIJava.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDTO {

    private Long id_empleado;
    private String nombre;
    private String apellido;
    private String cargo;
    private String telefono;
    private String correo;
}
