package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.dto.ClienteDTO;
import com.farmacia.sanrafael.APIJava.entities.ClienteEntity;
import com.farmacia.sanrafael.APIJava.mapper.ClienteMapper;
import com.farmacia.sanrafael.APIJava.payload.MessageResponse;
import com.farmacia.sanrafael.APIJava.service.ICliente;
import jakarta.validation.Valid;
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
        List<ClienteDTO> clientesDTO = iCliente.findAll().stream()
                .map(ClienteMapper::toDTO)
                .toList();

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Clientes recuperados con éxito.")
                .data(clientesDTO)
                .build(),
                HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/cliente")
    public ResponseEntity<?> saveCliente(@Valid @RequestBody ClienteDTO dto) {
        ClienteEntity cliente = ClienteMapper.toEntity(dto);
        ClienteEntity saved = iCliente.save(cliente);

        return new ResponseEntity<>(MessageResponse.builder()
                .message(String.format("Cliente %s %s guardado con éxito.", saved.getNombre(), saved.getApellido()))
                .data(ClienteMapper.toDTO(saved))
                .build(), HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping("/ConsultaCliente")
    public ResponseEntity<?> findCliente(@RequestParam("id_cliente") long id_cliente) {
        ClienteEntity cliente = iCliente.findCustomer(id_cliente);
        ClienteDTO dto = ClienteMapper.toDTO(cliente);

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Cliente encontrado con éxito.")
                .data(dto)
                .build(),
                HttpStatus.OK);
    }





}
