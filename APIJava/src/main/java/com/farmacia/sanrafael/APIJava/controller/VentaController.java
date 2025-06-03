package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.dto.VentasDTO;
import com.farmacia.sanrafael.APIJava.entities.VentaEntity;
import com.farmacia.sanrafael.APIJava.mapper.VentasMapper;
import com.farmacia.sanrafael.APIJava.payload.MessageResponse;
import com.farmacia.sanrafael.APIJava.service.ICliente;
import com.farmacia.sanrafael.APIJava.service.IEmpleado;
import com.farmacia.sanrafael.APIJava.service.IVenta;
import jakarta.validation.Valid;
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
    @Autowired
    private ICliente clienteService;
    @Autowired
    private IEmpleado empleadoService;

    @Transactional(readOnly = true)
    @GetMapping("/ventas")
    public ResponseEntity<?> getProductos() {
        List<VentasDTO> ventasDTO = iVenta.findAll().stream()
                .map(VentasMapper::toDTO)
                .toList();

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Ventas recuperadas con éxito.")
                .data(ventasDTO)
                .build(),
                HttpStatus.OK);
    }


    @Transactional
    @PostMapping("/venta")
    public ResponseEntity<?> saveProducto(@Valid @RequestBody VentasDTO dto) {
        VentaEntity venta = VentasMapper.toEntity(dto);
        VentaEntity saved = iVenta.save(venta);

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Venta guardada con éxito.")
                .data(VentasMapper.toDTO(saved))
                .build(), HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping("/ConsultaVenta")
    public ResponseEntity<?> VentasMayoresA(@RequestParam("total") double total) {
        List<VentasDTO> resultado = iVenta.VentasMayoresA(total).stream()
                .map(VentasMapper::toDTO)
                .toList();

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Ventas mayores a $" + total + " encontradas con éxito.")
                .data(resultado)
                .build(),
                HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/venta/{id}")
    public ResponseEntity<?> updateVenta(@PathVariable Long id, @Valid @RequestBody VentasDTO dto) {
        VentaEntity existingVenta = iVenta.BuscarVenta(id);

        if (existingVenta == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Venta no encontrada.")
                    .build(), HttpStatus.NOT_FOUND);
        }

        existingVenta.setTotal(dto.getTotal());
        existingVenta.setEstado(dto.getEstado());

        VentaEntity updated = iVenta.save(existingVenta);

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Venta actualizada con éxito.")
                .data(VentasMapper.toDTO(updated))
                .build(), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/venta/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id) {
        VentaEntity existingVenta = iVenta.BuscarVenta(id);

        if (existingVenta == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Venta no encontrada.")
                    .build(), HttpStatus.NOT_FOUND);
        }

        iVenta.delete(id);

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Venta eliminada con éxito.")
                .build(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/venta/{id}")
    public ResponseEntity<?> getVentaById(@PathVariable Long id) {
        VentaEntity venta = iVenta.BuscarVenta(id);

        if (venta == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Venta no encontrada.")
                    .build(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Venta recuperada con éxito.")
                .data(VentasMapper.toDTO(venta))
                .build(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/buscarv")
    public ResponseEntity<?> getVentasPorFecha(@RequestParam("fecha") String fecha) {
        List<VentaEntity> ventas = iVenta.buscarPorFecha(fecha);

        List<VentasDTO> dtos = ventas.stream()
                .map(VentasMapper::toDTO)
                .toList();

        return new ResponseEntity<>(MessageResponse.builder()
                .message(String.format("Ventas del día %s recuperadas.", fecha))
                .data(dtos)
                .build(), HttpStatus.OK);
    }
}
