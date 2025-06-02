package com.farmacia.sanrafael.APIJava.service;

import com.farmacia.sanrafael.APIJava.dto.DetalleVentaDTO;
import com.farmacia.sanrafael.APIJava.entities.DetalleVentaEntity;
import java.util.List;

public interface IDetalleventa {
    List<DetalleVentaEntity> findAll();

    DetalleVentaEntity BuscarDVenta(long idProducto);

    DetalleVentaEntity save(DetalleVentaEntity venta);
    List<DetalleVentaDTO> Detalle_VentaN(long id_venta);

    void delete(long id_D_Venta);

}

