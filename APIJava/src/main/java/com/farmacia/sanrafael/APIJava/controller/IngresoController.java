package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.entities.IngresoEntity;
import com.farmacia.sanrafael.APIJava.service.IIngreso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/process")
public class IngresoController {
    @Autowired
    private IIngreso iIngreso;

    @Transactional(readOnly = true)
    @GetMapping("/ingresos")
    public List<IngresoEntity> getIgresos() {return iIngreso.findAll();}

    @Transactional
    @PostMapping("/ingreso")
    public IngresoEntity saveIngreso(@RequestBody IngresoEntity ingreso) {return iIngreso.save(ingreso);}
}
