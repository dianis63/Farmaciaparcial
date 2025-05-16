package com.farmacia.sanrafael.APIJava.service;

import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;
import com.farmacia.sanrafael.APIJava.entities.VentaEntity;

import java.util.List;

public interface IVenta {
    List<VentaEntity> findAll();
    VentaEntity save(VentaEntity venta);
    List<VentaEntity> VentasMayoresA(double totalm);

}

