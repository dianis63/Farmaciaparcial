package com.farmacia.sanrafael.APIJava.controller;
import com.farmacia.sanrafael.APIJava.entities.VentaEntity;
import com.farmacia.sanrafael.APIJava.service.IVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/process")
public class VentaController {
    @Autowired
    private IVenta iVenta;

    @Transactional(readOnly = true)
    @GetMapping("/ventas")
    public List<VentaEntity> getProductos() {
        return iVenta.findAll();
    }

    @Transactional
    @PostMapping("/venta")
    public VentaEntity saveProducto(@RequestBody VentaEntity venta) {
        return iVenta.save(venta);
    }
}
