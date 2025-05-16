package com.farmacia.sanrafael.APIJava.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import  org.springframework.stereotype.Repository;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {

    @Query("select e from EmpleadoEntity e where e.id_empleado = :id_empleado")
    List<EmpleadoEntity> Buscarempleado(@Param("id_empleado")long id_empleado);

}
