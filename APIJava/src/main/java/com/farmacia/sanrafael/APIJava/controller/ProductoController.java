package com.farmacia.sanrafael.APIJava.controller;
import com.farmacia.sanrafael.APIJava.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.farmacia.sanrafael.APIJava.entities.ProductoEntity;
import com.farmacia.    sanrafael.APIJava.service.IProducto;
import java.util.List;
@RestController
@RequestMapping("/process")
public class ProductoController {
    @Autowired
    private IProducto iProducto;

    @Transactional(readOnly = true)
    @GetMapping("/productos")
    public List<ProductoEntity> getProductos() {
        return iProducto.findAll();
    }

    @Transactional
    @PostMapping("/producto")
    public ProductoEntity saveProducto(@RequestBody ProductoEntity producto) {
        return iProducto.save(producto);
    }

    @Transactional(readOnly = true)
    @GetMapping("/ConsultaProducto")
    public ResponseEntity<?> findProduct(@RequestParam ("idProducto") long idProducto) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Producto encontrado con éxito.")
                .data(iProducto.findProduct(idProducto))
                .build(),
                HttpStatus.OK);
    }
}
