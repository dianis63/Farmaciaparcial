package com.farmacia.sanrafael.APIJava.mapper;
import com.farmacia.sanrafael.APIJava.dto.ProductoDTO;
import com.farmacia.sanrafael.APIJava.entities.ProductoEntity;

public class ProductoMapper {
    public static ProductoDTO toDTO(ProductoEntity entity) {
        if (entity == null) return null;

        return ProductoDTO.builder()
                .idProducto(entity.getIdProducto())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .precio(entity.getPrecio())
                .stock(entity.getStock())
                .fecha_vencimiento(entity.getFecha_vencimiento())
                .build();
    }

    public static ProductoEntity toEntity(ProductoDTO dto) {
        if (dto == null) return null;

        ProductoEntity entity = new ProductoEntity();
        entity.setIdProducto(dto.getIdProducto());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        entity.setFecha_vencimiento(dto.getFecha_vencimiento());
        return entity;
    }
}

