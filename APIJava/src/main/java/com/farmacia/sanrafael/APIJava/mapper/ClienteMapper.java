package com.farmacia.sanrafael.APIJava.mapper;

import com.farmacia.sanrafael.APIJava.dto.ClienteDTO;
import com.farmacia.sanrafael.APIJava.entities.ClienteEntity;

public class ClienteMapper {

    public static ClienteDTO toDTO(ClienteEntity entity) {
        if (entity == null) return null;

        return ClienteDTO.builder()
                .id_cliente(entity.getId_cliente())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .telefono(entity.getTelefono())
                .direccion(entity.getDireccion())
                .correo(entity.getCorreo())
                .build();
    }

    public static ClienteEntity toEntity(ClienteDTO dto) {
        if (dto == null) return null;

        ClienteEntity entity = new ClienteEntity();
        entity.setId_cliente(dto.getId_cliente());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setDireccion(dto.getDireccion());
        entity.setCorreo(dto.getCorreo());
        return entity;
    }
}
