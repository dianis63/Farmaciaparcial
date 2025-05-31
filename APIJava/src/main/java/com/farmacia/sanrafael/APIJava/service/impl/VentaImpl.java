package com.farmacia.sanrafael.APIJava.service.impl;

import com.farmacia.sanrafael.APIJava.entities.ProductoEntity;
import com.farmacia.sanrafael.APIJava.entities.VentaEntity;
import com.farmacia.sanrafael.APIJava.repository.VentaRepository;
import com.farmacia.sanrafael.APIJava.service.IVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaImpl implements IVenta {
    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<VentaEntity> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public VentaEntity BuscarVenta(long id_venta) {
        return ventaRepository.BuscarVenta(id_venta);
    }

    @Override
    public VentaEntity save(VentaEntity venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public List<VentaEntity> VentasMayoresA(double totalm) {
        return ventaRepository.VentasMayoresA(totalm);
    }

    @Override
    public void delete(long idProducto) {
        ventaRepository.deleteById(idProducto);
    }
}
