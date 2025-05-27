package com.farmacia.sanrafael.APIJava.mapper;

import com.farmacia.sanrafael.APIJava.dto.IngresoDTO;
import com.farmacia.sanrafael.APIJava.entities.IngresoEntity;

public class IngresoMapper {

    public static IngresoDTO toDTO(IngresoEntity entity) {
        if (entity == null) return null;

        return IngresoDTO.builder()
                .id_ingreso(entity.getId_ingreso())
                .id_producto(entity.getId_producto())
                .cantidad(entity.getCantidad())
                .precio_compra(entity.getPrecio_compra())
                .fecha_ingreso(entity.getFecha_ingreso())
                .id_empleado(entity.getId_empleado())
                .build();
    }
    public static IngresoEntity toEntity(IngresoDTO dto) {
        if (dto == null) return null;

        IngresoEntity entity = new IngresoEntity();
        entity.setId_ingreso(dto.getId_ingreso());
        entity.setId_producto(dto.getId_producto());
        entity.setCantidad(dto.getCantidad());
        entity.setPrecio_compra(dto.getPrecio_compra());
        entity.setFecha_ingreso(dto.getFecha_ingreso());
        entity.setId_empleado(dto.getId_empleado());
        return entity;
    }
}
