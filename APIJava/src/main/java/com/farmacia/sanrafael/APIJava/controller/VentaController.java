package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.dto.ProductoDTO;
import com.farmacia.sanrafael.APIJava.dto.VentasDTO;
import com.farmacia.sanrafael.APIJava.entities.ProductoEntity;
import com.farmacia.sanrafael.APIJava.entities.VentaEntity;
import com.farmacia.sanrafael.APIJava.mapper.ProductoMapper;
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
}
