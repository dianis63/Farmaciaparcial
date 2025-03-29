package com.farmacia.sanrafael.APIJava.entities;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "Empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoEntity {
    @Id
    @Column(columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_empleado;

    @Column(columnDefinition = "VARCHAR(100)", length = 100, nullable = false)
    private String nombre;

    @Column(columnDefinition = "VARCHAR(100)", length = 100, nullable = false)
    private String apellido;

    @Column(columnDefinition = "VARCHAR(50)", length = 50)
    private String cargo;

    @Column(columnDefinition = "VARCHAR(20)", length = 20)
    private String telefono;

    @Column(columnDefinition = "VARCHAR(25)", length = 25)
    private String correo;
}
