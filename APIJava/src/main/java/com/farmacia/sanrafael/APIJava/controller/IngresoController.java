package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.dto.IngresoDTO;
import com.farmacia.sanrafael.APIJava.entities.IngresoEntity;
import com.farmacia.sanrafael.APIJava.mapper.IngresoMapper;
import com.farmacia.sanrafael.APIJava.payload.MessageResponse;
import com.farmacia.sanrafael.APIJava.service.IIngreso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/process")
public class IngresoController {
    @Autowired
    private IIngreso iIngreso;

    @Transactional(readOnly = true)
    @GetMapping("/ingresos")
    public ResponseEntity<?> getIngresos() {
        List<IngresoDTO> ingresosDTO = iIngreso.findAll().stream()
                .map(IngresoMapper::toDTO)
                .toList();

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Ingresos recuperados con éxito.")
                .data(ingresosDTO)
                .build(),
                HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/ingreso")
    public ResponseEntity<?> saveIngreso(@Valid @RequestBody IngresoDTO dto) {
        IngresoEntity ingreso = IngresoMapper.toEntity(dto);
        IngresoEntity saved = iIngreso.save(ingreso);

        return new ResponseEntity<>(MessageResponse.builder()
                .message(String.format("Ingreso %s con el precio de compra de $%s guardado con éxito.", saved.getId_ingreso(), saved.getPrecio_compra()))
                .data(IngresoMapper.toDTO(saved))
                .build(),
                HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/ConsultaIngreso")
    public ResponseEntity<?> findQuantity(@RequestParam ("cantidad") Integer cantidad) {
        List<IngresoDTO> ingreso = iIngreso.findQuantity(cantidad).stream()
                .map(IngresoMapper::toDTO)
                .toList();

        return new ResponseEntity<>(MessageResponse.builder()
                .message(String.format("Ingresos menores a %d unidades encontrados con éxito.", cantidad))
                .data(ingreso)
                .build(),
                HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/ingreso/{id}")
    public ResponseEntity<?> updateIngreso(@PathVariable Long id, @Valid @RequestBody IngresoDTO dto) {
        IngresoEntity existingIngreso = iIngreso.findById(id).stream().findFirst().orElse(null);

        if (existingIngreso == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Ingreso no encontrado.")
                    .build(), HttpStatus.NOT_FOUND);
        }

        existingIngreso.setId_producto(dto.getId_producto());
        existingIngreso.setCantidad(dto.getCantidad());
        existingIngreso.setPrecio_compra(dto.getPrecio_compra());
        existingIngreso.setFecha_ingreso(dto.getFecha_ingreso());
        existingIngreso.setId_empleado(dto.getId_empleado());

        IngresoEntity updated = iIngreso.save(existingIngreso);

        return new ResponseEntity<>(MessageResponse.builder()
                .message(String.format("Ingreso %s actualizado con éxito.", updated.getId_ingreso()))
                .data(IngresoMapper.toDTO(updated))
                .build(), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/ingreso/{id}")
    public ResponseEntity<?> deleteIngreso(@PathVariable Long id) {
        IngresoEntity existingIngreso = iIngreso.findById(id).stream().findFirst().orElse(null);

        if (existingIngreso == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Ingreso no encontrado.")
                    .build(), HttpStatus.NOT_FOUND);
        }

        iIngreso.delete(id);

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Ingreso eliminado con éxito.")
                .build(), HttpStatus.OK);
    }
}
