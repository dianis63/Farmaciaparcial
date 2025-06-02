package com.farmacia.sanrafael.APIJava.service;
import com.farmacia.sanrafael.APIJava.entities.VentaEntity;

import java.util.List;

public interface IVenta {
    List<VentaEntity> findAll();

    VentaEntity BuscarVenta(long idProducto);

    VentaEntity save(VentaEntity venta);

    List<VentaEntity> VentasMayoresA(double totalm);

    void delete(long idVenta);

    List<VentaEntity> buscarPorFecha(String fecha);

}

