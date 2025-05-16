package com.farmacia.sanrafael.APIJava.repository;
import com.farmacia.sanrafael.APIJava.entities.IngresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngresoRepository extends JpaRepository<IngresoEntity, Long> {

    @Query("select i from IngresoEntity i where i.cantidad < :cantidad")
    List<IngresoEntity> findQuantity(@Param("cantidad")Integer cantidad);
}
