package com.farmacia.sanrafael.APIJava.repository;

import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;
import com.farmacia.sanrafael.APIJava.entities.ProductoEntity;
import com.farmacia.sanrafael.APIJava.entities.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<VentaEntity, Long> {

    @Query("select v from VentaEntity v where v.total >= :totalm")
    List<VentaEntity> VentasMayoresA(@Param("totalm")double totalm);

    @Query("select e from VentaEntity e where e.id_venta = :id_venta")
    VentaEntity BuscarVenta(@Param("id_venta") long id_venta);

}

