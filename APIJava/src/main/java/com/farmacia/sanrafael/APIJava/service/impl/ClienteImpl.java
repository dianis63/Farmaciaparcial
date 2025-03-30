package com.farmacia.sanrafael.APIJava.service.impl;
import com.farmacia.sanrafael.APIJava.entities.ClienteEntity;
import com.farmacia.sanrafael.APIJava.repository.ClienteRepository;
import com.farmacia.sanrafael.APIJava.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ClienteImpl implements ICliente {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteEntity> findAll() { return clienteRepository.findAll();}

    @Override
    public ClienteEntity save(ClienteEntity cliente) {return clienteRepository.save(cliente);}
}
