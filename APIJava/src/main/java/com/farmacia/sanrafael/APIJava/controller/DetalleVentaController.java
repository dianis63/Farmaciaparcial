package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.entities.DetalleVentaEntity;
import com.farmacia.sanrafael.APIJava.service.IDetalleventa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/process")
public class DetalleVentaController {
    @Autowired
    private IDetalleventa iDetalleventa;

    @Transactional(readOnly = true)
    @GetMapping("/detalle_ventas")
    public List<DetalleVentaEntity> getProductos() {
        return iDetalleventa.findAll();
    }

    @Transactional
    @PostMapping("/detalle_venta")
    public DetalleVentaEntity saveProducto(@RequestBody DetalleVentaEntity detalleventa) {
        return iDetalleventa.save(detalleventa);
    }
}
