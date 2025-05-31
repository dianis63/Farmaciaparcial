package com.farmacia.sanrafael.APIJava.repository;
import com.farmacia.sanrafael.APIJava.entities.DetalleVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVentaEntity, Long> {

    @Query("select dv from DetalleVentaEntity dv where dv.id_venta = :id_venta")
    List<DetalleVentaEntity> Detalle_VentaN(@Param("id_venta")long id_venta);

    @Query("select e from DetalleVentaEntity e where e.id_detalle = :id_detalle")
    DetalleVentaEntity BuscarDVenta(@Param("id_detalle") long id_detalle);

}
