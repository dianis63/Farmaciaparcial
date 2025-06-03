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
    public ResponseEntity<?> getDetallesVentas() {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Detalles de venta recuperados con éxito.")
                .data(iDetalleventa.findAll())
                .build(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/detalle_venta")
    public ResponseEntity<?> saveDetalleVenta(@Valid @RequestBody DetalleVentaEntity detalleVenta) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Detalle de venta guardado con éxito.")
                .data(iDetalleventa.save(detalleVenta))
                .build(), HttpStatus.OK);
    }


    @Transactional(readOnly = true)
    @GetMapping("/ConsultaDVenta")
    public ResponseEntity<?> getDetallesPorVenta(@RequestParam("id_venta") long idVenta) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message(String.format("Detalles de la venta N° %d encontrados con éxito.", idVenta))
                .data(iDetalleventa.Detalle_VentaN(idVenta))
                .build(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/detalle_venta/{id}")
    public ResponseEntity<?> getDetallePorId(@PathVariable long id) {
        DetalleVentaEntity detalle = iDetalleventa.BuscarDVenta(id);
        if (detalle == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Detalle de venta no encontrado.")
                    .build(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Detalle de venta encontrado con éxito.")
                .data(detalle)
                .build(), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/detalle_venta/{id}")
    public ResponseEntity<?> deleteDetalle(@PathVariable long id) {
        DetalleVentaEntity detalle = iDetalleventa.BuscarDVenta(id);
        if (detalle == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Detalle de venta no encontrado.")
                    .build(), HttpStatus.NOT_FOUND);
        }

        iDetalleventa.delete(id);

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Detalle de venta eliminado con éxito.")
                .build(), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/detalle_venta/{id}")
    public ResponseEntity<?> updateDetalle(@PathVariable long id, @Valid @RequestBody DetalleVentaEntity updatedData) {
        DetalleVentaEntity existing = iDetalleventa.BuscarDVenta(id);
        if (existing == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Detalle de venta no encontrado.")
                    .build(), HttpStatus.NOT_FOUND);
        }

        existing.setId_producto(updatedData.getId_producto());
        existing.setCantidad(updatedData.getCantidad());

        DetalleVentaEntity updated = iDetalleventa.save(existing);

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Detalle de venta actualizado con éxito.")
                .data(updated)
                .build(), HttpStatus.OK);
    }
}
