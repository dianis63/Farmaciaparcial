package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.entities.DetalleVentaEntity;
import com.farmacia.sanrafael.APIJava.payload.MessageResponse;
import com.farmacia.sanrafael.APIJava.service.IDetalleventa;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process")
public class DetalleVentaController {
    @Autowired
    private IDetalleventa iDetalleventa;

    @Transactional(readOnly = true)
    @GetMapping("/detalle_ventas")
    public ResponseEntity<?> getDProductos() {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Detalles de venta recuperados con éxito.")
                .data(iDetalleventa.findAll())
                .build(),
                HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/detalle_venta")
    public ResponseEntity<?> saveProducto(@Valid @RequestBody DetalleVentaEntity detalleventa) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Detalle de venta guardado con éxito.")
                .data(iDetalleventa.save(detalleventa))
                .build(),
                HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/ConsultaDVenta")
    public ResponseEntity<?> Detalle_VentaN(@RequestParam("id_venta") long id_venta) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message(String.format("Detalle de venta N° %d encontrado con éxito.", id_venta))
                .data(iDetalleventa.Detalle_VentaN(id_venta))
                .build(),
                HttpStatus.OK);
    }
}
