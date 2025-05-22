package com.farmacia.sanrafael.APIJava.service;

import com.farmacia.sanrafael.APIJava.entities.DetalleVentaEntity;

import java.util.List;

public interface IDetalleventa {
    List<DetalleVentaEntity> findAll();
    DetalleVentaEntity save(DetalleVentaEntity venta);
    List<DetalleVentaEntity> Detalle_VentaN(long id_venta);

}

