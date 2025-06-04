package com.farmacia.sanrafael.APIJava.mapper;

import com.farmacia.sanrafael.APIJava.dto.IngresoDTO;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;
import com.farmacia.sanrafael.APIJava.entities.IngresoEntity;
import com.farmacia.sanrafael.APIJava.entities.ProductoEntity;

public class IngresoMapper {

    public static IngresoDTO toDTO(IngresoEntity entity) {
        if (entity == null) return null;

        return IngresoDTO.builder()
                .id_ingreso(entity.getId_ingreso())
                .id_producto(entity.getProducto().getIdProducto())
                .nombre_producto((entity.getProducto().getNombre()+" "+entity.getProducto().getPrecio()))
                .cantidad(entity.getCantidad())
                .precio_compra(entity.getPrecio_compra())
                .fecha_ingreso(entity.getFecha_ingreso())
                .id_empleado(entity.getEmpleado().getId_empleado())
                .nombre_empleado(entity.getEmpleado().getNombre()+" "+entity.getEmpleado().getApellido())
                .build();
    }
    public static IngresoEntity toEntity(IngresoDTO dto) {
        if (dto == null) return null;

        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setId_empleado(dto.getId_empleado());

        ProductoEntity producto = new ProductoEntity();
        producto.setIdProducto(dto.getId_producto());

        IngresoEntity entity = new IngresoEntity();
        entity.setId_ingreso(dto.getId_ingreso());
        entity.setProducto(producto);
        entity.setCantidad(dto.getCantidad());
        entity.setPrecio_compra(dto.getPrecio_compra());
        entity.setFecha_ingreso(dto.getFecha_ingreso());
        entity.setEmpleado(empleado);
        return entity;
    }
}
