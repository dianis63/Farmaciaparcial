package com.farmacia.sanrafael.APIJava.service.impl;

import com.farmacia.sanrafael.APIJava.dto.DetalleVentaDTO;
import com.farmacia.sanrafael.APIJava.entities.DetalleVentaEntity;
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
    public DetalleVentaEntity BuscarDVenta(long id_venta) {
        return DetalleventaRepository.BuscarDVenta(id_venta);
    }

    @Override
    public List<DetalleVentaDTO> Detalle_VentaN(long id_venta) {
        return DetalleventaRepository.findDetalleVentaConNombreProducto(id_venta);
    }

    @Override
    public void delete(long idProducto) {
        DetalleventaRepository.deleteById(idProducto);
    }
}
