package com.farmacia.sanrafael.APIJava.service;

import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;

import java.util.List;

public interface IEmpleado {

    List<EmpleadoEntity> findAll();
    EmpleadoEntity save(EmpleadoEntity empleado);

    List<EmpleadoEntity> findEmployee(long id_empleado);

}
