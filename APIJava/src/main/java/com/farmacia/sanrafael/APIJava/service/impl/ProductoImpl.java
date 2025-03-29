package com.farmacia.sanrafael.APIJava.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmacia.sanrafael.APIJava.entities.ProductoEntity;
import com.farmacia.sanrafael.APIJava.repository.ProductoRepository;
import com.farmacia.sanrafael.APIJava.service.IProducto;
import java.util.List;
@Service
public class ProductoImpl implements IProducto {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoEntity> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public ProductoEntity save(ProductoEntity producto) {
        return productoRepository.save(producto);
    }
}
