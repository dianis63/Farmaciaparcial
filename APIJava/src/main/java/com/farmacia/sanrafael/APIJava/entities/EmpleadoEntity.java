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
    private Long id_empleado;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String nombre;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String apellido;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String cargo;

    @Column(columnDefinition = "VARCHAR(20)")
    private String telefono;

    @Column(columnDefinition = "VARCHAR(25)")
    private String correo;
}
