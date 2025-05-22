package com.farmacia.sanrafael.APIJava.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {
    @Id
    @Column(columnDefinition = "INT", name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @NotBlank(message = "El nombre es obligatorio.")
    @Column(columnDefinition = "Varchar(100)", nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio.")
    @Column(columnDefinition = "Varchar(100)", nullable = false)
    private String apellido;

    @Column(columnDefinition = "Varchar(20)")
    private String telefono;

    @Column(columnDefinition = "Varchar(200)")
    private String direccion;

    @Column(columnDefinition = "Varchar(25)")
    @Email(message = "El correo debe tener un formato válido.")
    private String correo;
}
