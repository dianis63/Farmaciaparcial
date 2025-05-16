package com.farmacia.sanrafael.APIJava.service;
import com.farmacia.sanrafael.APIJava.entities.ClienteEntity;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;

import java.util.List;
public interface ICliente {
    List<ClienteEntity> findAll();
    ClienteEntity save(ClienteEntity cliente);
    List<ClienteEntity> findCustomer(long id_cliente);
}
