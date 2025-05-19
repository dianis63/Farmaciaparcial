package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.entities.VentaEntity;
import com.farmacia.sanrafael.APIJava.payload.MessageResponse;
import com.farmacia.sanrafael.APIJava.service.IVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getProductos() {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Ventas recuperadas con éxito.")
                .data(iVenta.findAll())
                .build(),
                HttpStatus.OK);
    }


    @Transactional
    @PostMapping("/venta")
    public ResponseEntity<?> saveProducto(@RequestBody VentaEntity venta) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Venta guardada con éxito.")
                .data(iVenta.save(venta))
                .build(),
                HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/ConsultaVenta")
    public ResponseEntity<?> VentasMayoresA(@RequestParam ("total") double total) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Ventas mayores a $" + total + " encontradas con éxito.")
                .data(iVenta.VentasMayoresA(total))
                .build(),
                HttpStatus.OK);
    }
}
