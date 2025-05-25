package com.farmacia.sanrafael.APIJava.controller;

import com.farmacia.sanrafael.APIJava.payload.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.farmacia.sanrafael.APIJava.entities.ProductoEntity;
import com.farmacia.sanrafael.APIJava.service.IProducto;
import com.farmacia.sanrafael.APIJava.dto.ProductoDTO;
import com.farmacia.sanrafael.APIJava.mapper.ProductoMapper;

import java.util.List;

@RestController
@RequestMapping("/process")
public class ProductoController {

    @Autowired
    private IProducto iProducto;

    @Transactional(readOnly = true)
    @GetMapping("/productos")
    public ResponseEntity<?> getProductos() {
        List<ProductoDTO> productosDTO = iProducto.findAll().stream()
                .map(ProductoMapper::toDTO)
                .toList();

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Productos recuperados con éxito.")
                .data(productosDTO)
                .build(),
                HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/producto")
    public ResponseEntity<?> saveProducto(@Valid @RequestBody ProductoDTO dto) {
        ProductoEntity producto = ProductoMapper.toEntity(dto);
        ProductoEntity saved = iProducto.save(producto);

        return new ResponseEntity<>(MessageResponse.builder()
                .message(String.format("Producto %s guardado con éxito.", saved.getNombre()))
                .data(ProductoMapper.toDTO(saved))
                .build(), HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping("/ConsultaProducto")
    public ResponseEntity<?> findProduct(@RequestParam("idProducto") long idProducto) {
        List<ProductoDTO> resultado = iProducto.findProduct(idProducto).stream()
                .map(ProductoMapper::toDTO)
                .toList();

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Producto encontrado con éxito.")
                .data(resultado)
                .build(),
                HttpStatus.OK);
    }
}
