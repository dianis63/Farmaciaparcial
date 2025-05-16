package com.farmacia.sanrafael.APIJava.service;
import com.farmacia.sanrafael.APIJava.entities.EmpleadoEntity;
import com.farmacia.sanrafael.APIJava.entities.ProductoEntity;
import java.util.List;
public interface IProducto {
    List<ProductoEntity> findAll();
    ProductoEntity save(ProductoEntity producto);
    List<ProductoEntity> findProduct(long idProducto);
}
