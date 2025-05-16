package com.farmacia.sanrafael.APIJava.controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.farmacia.sanrafael.APIJava.service.IEmpleado;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;
import java.util.List;
@RestController
@RequestMapping("/process")
public class EmpleadoController {

    @Autowired
    private IEmpleado iEmpleado;

    @Transactional(readOnly = true)
    @GetMapping("/empleados")
    public List<EmpleadoEntity> getEmpleados() {
        return iEmpleado.findAll();
    }

    @Transactional
    @PostMapping("/empleado")
    public EmpleadoEntity save(@RequestBody EmpleadoEntity empleado) {
        return iEmpleado.save(empleado);
    }

    @Transactional(readOnly = true)
    @GetMapping("/ConsultaEmpleado/{id_empleado}")
    public List<EmpleadoEntity> findPriceLess(@RequestParam("id_empleado") long id_empleado) {
        return iEmpleado.findPriceLess(id_empleado);
    }
}
