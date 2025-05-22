package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.payload.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.farmacia.sanrafael.APIJava.service.IEmpleado;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;

@RestController
@RequestMapping("/process")
public class EmpleadoController {

    @Autowired
    private IEmpleado iEmpleado;

    @Transactional(readOnly = true)
    @GetMapping("/empleados")
    public ResponseEntity<?> getEmpleados() {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Empleados recuperados con éxito.")
                .data(iEmpleado.findAll())
                .build(),
                HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/empleado")
    public ResponseEntity<?> save(@Valid @RequestBody EmpleadoEntity empleado) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message(String.format("Empleado %s %s guardado con éxito.", empleado.getNombre(), empleado.getApellido()))
                .data(iEmpleado.save(empleado))
                .build(),
                HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/ConsultaEmpleado")
    public ResponseEntity<?> findEmployee(@RequestParam ("id_empleado") long id_empleado) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Empleado encontrado con éxito.")
                .data(iEmpleado.findEmployee(id_empleado))
                .build(),
                HttpStatus.OK);
    }
}
