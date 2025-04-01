package com.farmacia.sanrafael.APIJava.service;
import com.farmacia.sanrafael.APIJava.entities.IngresoEntity;
import java.util.List;

public interface IIngreso {
    List<IngresoEntity> findAll();
    IngresoEntity save(IngresoEntity ingreso);
}
