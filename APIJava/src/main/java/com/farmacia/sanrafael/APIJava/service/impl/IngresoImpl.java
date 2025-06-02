package com.farmacia.sanrafael.APIJava.service.impl;

import com.farmacia.sanrafael.APIJava.entities.IngresoEntity;
import com.farmacia.sanrafael.APIJava.repository.IngresoRepository;
import com.farmacia.sanrafael.APIJava.service.IIngreso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngresoImpl implements IIngreso {
    @Autowired
    private IngresoRepository ingresoRepository;

    @Override
    public List<IngresoEntity> findAll() {return ingresoRepository.findAll();}

    @Override
    public IngresoEntity save(IngresoEntity ingresoEntity) {return ingresoRepository.save(ingresoEntity);}

    @Override
    public List<IngresoEntity> findQuantity(Integer cantidad) {return ingresoRepository.findQuantity(cantidad);
    }

    @Override
    public List<IngresoEntity> findById(Long id_ingreso) {return ingresoRepository.findByIdIngreso(id_ingreso);}

    @Override
    public void delete(Long id_ingreso) {ingresoRepository.deleteById(id_ingreso);}
}
