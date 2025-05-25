package com.farmacia.sanrafael.APIJava.mapper;

import com.farmacia.sanrafael.APIJava.dto.VentasDTO;
import com.farmacia.sanrafael.APIJava.entities.ClienteEntity;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;
import com.farmacia.sanrafael.APIJava.entities.VentaEntity;

public class VentasMapper {

    public static VentasDTO toDTO(VentaEntity entity) {
        if (entity == null) return null;

        return VentasDTO.builder()
                .id_venta(entity.getId_venta())
                .fecha(entity.getFecha())
                .total(entity.getTotal())
                .id_cliente(entity.getCliente().getId_cliente())
                .id_empleado(entity.getEmpleado().getId_empleado())
                //Añadidos para poder mostrar los nombres de cliente y empleado
                .nombre_cliente(entity.getCliente().getNombre()+" "+entity.getCliente().getApellido())
                .nombre_empleado(entity.getEmpleado().getNombre()+" "+entity.getEmpleado().getApellido())
                .estado(entity.getEstado())
                .build();
    }

    // Este metodo se usará para el POST y solo necesita cliente/empleado con id
    public static VentaEntity toEntity(VentasDTO dto) {
        if (dto == null) return null;

        ClienteEntity cliente = new ClienteEntity();
        cliente.setId_cliente(dto.getId_cliente());

        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setId_empleado(dto.getId_empleado());

        VentaEntity entity = new VentaEntity();
        entity.setId_venta(dto.getId_venta());
        entity.setFecha(dto.getFecha());
        entity.setTotal(dto.getTotal());
        entity.setEstado(dto.getEstado());
        entity.setCliente(cliente);
        entity.setEmpleado(empleado);

        return entity;
    }
}
