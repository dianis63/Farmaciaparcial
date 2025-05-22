package com.farmacia.sanrafael.APIJava.repository;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.farmacia.sanrafael.APIJava.entities.ProductoEntity;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    @Query("select e from ProductoEntity e where e.idProducto = :idProducto")
    List<ProductoEntity> Buscarproducto(@Param("idProducto")long idProducto);
}

