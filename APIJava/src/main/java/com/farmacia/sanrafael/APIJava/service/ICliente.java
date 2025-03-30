package com.farmacia.sanrafael.APIJava.service;
import com.farmacia.sanrafael.APIJava.entities.ClienteEntity;
import java.util.List;
public interface ICliente {
    List<ClienteEntity> findAll();
    ClienteEntity save(ClienteEntity cliente);
}
