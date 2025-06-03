package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.dto.EmpleadoDTO;
import com.farmacia.sanrafael.APIJava.mapper.EmpleadoMapper;
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



    @Transactional
    @PutMapping("/empleado/{id}")
    public ResponseEntity<?> updateEmpleado(@PathVariable Long id, @Valid @RequestBody EmpleadoDTO dto) {
        EmpleadoEntity existingEmpleado = iEmpleado.findEmployee(id);

        if (existingEmpleado == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Empleado no encontrado.")
                    .build(), HttpStatus.NOT_FOUND);
        }

        existingEmpleado.setNombre(dto.getNombre());
        existingEmpleado.setApellido(dto.getApellido());
        existingEmpleado.setTelefono(dto.getTelefono());
        existingEmpleado.setCorreo(dto.getCorreo());
        existingEmpleado.setCargo(dto.getCargo());

        EmpleadoEntity updated = iEmpleado.save(existingEmpleado);

        return new ResponseEntity<>(MessageResponse.builder()
                .message(String.format("Empleado %s %s actualizado con éxito.", updated.getNombre(), updated.getApellido()))
                .data(EmpleadoMapper.toDTO(updated))
                .build(), HttpStatus.OK);
    }


    @Transactional
    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Long id) {
        EmpleadoEntity existingEmpleado = iEmpleado.findEmployee(id);

        if (existingEmpleado == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Empleado no encontrado.")
                    .build(), HttpStatus.NOT_FOUND);
        }

        iEmpleado.delete(id);

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Empleado eliminado con éxito.")
                .build(), HttpStatus.OK);
    }
}
