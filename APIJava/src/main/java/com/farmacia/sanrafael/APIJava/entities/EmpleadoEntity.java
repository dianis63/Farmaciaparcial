package com.farmacia.sanrafael.APIJava.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "El nombre del empleado/a es obligatorio.")
    private String nombre;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    @NotBlank(message = "El apellido del empleado/a es obligatorio.")
    private String apellido;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    @NotBlank(message = "El cargo del empleado/a es obligatorio.")
    private String cargo;

    @Column(columnDefinition = "VARCHAR(20)")
    private String telefono;

    @Column(columnDefinition = "VARCHAR(25)")
    @Email(message = "El correo debe tener un formato válido.")
    private String correo;
}
