package com.farmacia.sanrafael.APIJava.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;
import java.util.List;
import com.farmacia.sanrafael.APIJava.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import com.farmacia.sanrafael.APIJava.service.IEmpleado;
@Service
public class EmpleadoImpl implements IEmpleado {

    @Autowired
    private EmpleadoRepository EmpleadoRepository;


    @Override
    public List<EmpleadoEntity> findAll(){
        return EmpleadoRepository.findAll();
    }

    @Override
    public EmpleadoEntity save(EmpleadoEntity empleado) {
        return EmpleadoRepository.save(empleado);
    }

    @Override
    public List<EmpleadoEntity> findEmployee(long id_empleado) {
        return EmpleadoRepository.Buscarempleado(id_empleado);
    }

}
