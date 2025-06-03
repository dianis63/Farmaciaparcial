package com.farmacia.sanrafael.APIJava.entities;
import com.farmacia.sanrafael.APIJava.entities.DetalleVentaEntity;
import com.farmacia.sanrafael.APIJava.entities.IngresoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class ProductoEntity {
    @Id
    @Column(columnDefinition = "INT", name="id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    @NotBlank(message = "El nombre del producto es obligatorio.")
    private String nombre;

    @Column(columnDefinition = "VARCHAR(200)")
    private String descripcion;

    @Column(columnDefinition = "DOUBLE", nullable = false)
    @NotNull(message = "El precio es obligatorio.")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0.")
    private Double precio;

    @Column(columnDefinition = "INT", nullable = false)
    @NotNull(message = "El stock es obligatorio.")
    @Min(value = 0, message = "El stock no puede ser negativo.")
    private Integer stock;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date fecha_vencimiento;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.PERSIST)
    private List<DetalleVentaEntity> detallesVenta;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.PERSIST)
    private List<IngresoEntity> ingresos;


}
