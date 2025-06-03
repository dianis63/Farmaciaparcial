package com.farmacia.sanrafael.APIJava.mapper;

import com.farmacia.sanrafael.APIJava.dto.EmpleadoDTO;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;

public class EmpleadoMapper {

    public static EmpleadoDTO toDTO(EmpleadoEntity entity) {
        if (entity == null) return null;

        return EmpleadoDTO.builder()
                .id_Empleado(entity.getId_empleado())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .telefono(entity.getTelefono())
                .correo(entity.getCorreo())
                .build();
    }

    public static EmpleadoEntity toEntity(EmpleadoDTO dto) {
        if (dto == null) return null;

        EmpleadoEntity entity = new EmpleadoEntity();
        entity.setId_empleado(dto.getId_Empleado());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());
        return entity;
    }
}