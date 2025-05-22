package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.entities.IngresoEntity;
import com.farmacia.sanrafael.APIJava.payload.MessageResponse;
import com.farmacia.sanrafael.APIJava.service.IIngreso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process")
public class IngresoController {
    @Autowired
    private IIngreso iIngreso;

    @Transactional(readOnly = true)
    @GetMapping("/ingresos")
    public ResponseEntity<?> getIngresos() {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Ingresos recuperados con éxito.")
                .data(iIngreso.findAll())
                .build(),
                HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/ingreso")
    public ResponseEntity<?> save(@Valid @RequestBody IngresoEntity ingreso) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Ingreso guardado con éxito.")
                .data(iIngreso.save(ingreso))
                .build(),
                HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/ConsultaIngreso")
    public ResponseEntity<?> findQuantity(@RequestParam ("cantidad") Integer cantidad) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message(String.format("Ingreso con cantidad %d encontrado con éxito.", cantidad))
                .data(iIngreso.findQuantity(cantidad))
                .build(),
                HttpStatus.OK);
    }
}
