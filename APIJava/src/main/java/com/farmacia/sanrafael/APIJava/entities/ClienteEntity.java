package com.farmacia.sanrafael.APIJava.entities;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {
    @Id
    @Column(columnDefinition = "INT",name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;

    @Column(columnDefinition = "Varchar(100)", length = 100)
    private String nombre;

    @Column(columnDefinition = "Varchar(100)", length = 100)
    private String apellido;

    @Column(columnDefinition = "Varchar(20)", length = 20)
    private String telefono;

    @Column(columnDefinition = "Varchar(200)", length = 200)
    private String direccion;

    @Column(columnDefinition = "Varchar(25)", length = 25)
    private String correo;
}
