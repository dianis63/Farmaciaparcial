package com.farmacia.sanrafael.APIJava.controller;
import com.farmacia.sanrafael.APIJava.entities.ClienteEntity;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;
import com.farmacia.sanrafael.APIJava.payload.MessageResponse;
import com.farmacia.sanrafael.APIJava.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/process")
public class ClienteController {
    @Autowired
    private ICliente iCliente;

    @Transactional(readOnly = true)
    @GetMapping("/clientes")
    public ResponseEntity<?> getClientes() {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Clientes recuperados con éxito.")
                .data(iCliente.findAll())
                .build(),
                HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/cliente")
    public ResponseEntity<?> save(@RequestBody ClienteEntity cliente) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Cliente guardado con éxito.")
                .data(iCliente.save(cliente))
                .build(),
                HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/ConsultaCliente")
    public ResponseEntity<?> findCustomer(@RequestParam ("id_cliente") long id_cliente) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Cliente encontrado con con éxito.")
                .data(iCliente.findCustomer(id_cliente))
                .build(),
                HttpStatus.OK);
    }
}
