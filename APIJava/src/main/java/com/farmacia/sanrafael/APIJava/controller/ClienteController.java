package com.farmacia.sanrafael.APIJava.controller;
import com.farmacia.sanrafael.APIJava.entities.ClienteEntity;
import com.farmacia.sanrafael.APIJava.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ClienteEntity> getClientes() {
        return iCliente.findAll();
    }

    @Transactional
    @PostMapping("/cliente")
    public ClienteEntity saveCliente(@RequestBody ClienteEntity cliente) {
        return iCliente.save(cliente);
    }
}
