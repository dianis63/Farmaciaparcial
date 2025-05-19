package com.farmacia.sanrafael.APIJava.service.impl;

import com.farmacia.sanrafael.APIJava.entities.DetalleVentaEntity;
import com.farmacia.sanrafael.APIJava.entities.VentaEntity;
import com.farmacia.sanrafael.APIJava.repository.DetalleVentaRepository;
import com.farmacia.sanrafael.APIJava.service.IDetalleventa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaImpl implements IDetalleventa {
    @Autowired
    private DetalleVentaRepository DetalleventaRepository;

    @Override
    public List<DetalleVentaEntity> findAll() {
        return DetalleventaRepository.findAll();
    }

    @Override
    public DetalleVentaEntity save(DetalleVentaEntity detalleventa) {
        return DetalleventaRepository.save(detalleventa);
    }

    @Override
    public List<DetalleVentaEntity> Detalle_VentaN(long id_venta) {
        return DetalleventaRepository.Detalle_VentaN(id_venta);
    }
}
