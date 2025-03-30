package com.farmacia.sanrafael.APIJava.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import  org.springframework.stereotype.Repository;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {

}
