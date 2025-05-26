package com.farmacia.sanrafael.APIJava.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {
    private Long id_cliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String correo;
}
