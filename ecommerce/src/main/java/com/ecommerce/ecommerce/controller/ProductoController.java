package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.DTO.ProductoRequestDTO;
import com.ecommerce.ecommerce.DTO.ProductoResponseDTO;
import com.ecommerce.ecommerce.mapper.ProductoMapper;
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> agregarProducto(@RequestBody ProductoRequestDTO dto) {

        ProductoResponseDTO response = service.agregarProducto(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}") 
    public ResponseEntity<ProductoResponseDTO> modificarProducto(@PathVariable Long id, @RequestBody ProductoRequestDTO dto) {

        ProductoResponseDTO response = service.modificarProducto(id, dto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> traerProductoPorId(@PathVariable Long id) {

        Producto producto = service.traerProductoPorId(id);

        return ResponseEntity.ok(producto);

    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> traerProductos() {

        List<ProductoResponseDTO> productos = service.traerProductos().stream().map(ProductoMapper::toDto).toList();

        return ResponseEntity.ok(productos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {

        service.eliminarProducto(id);

        return ResponseEntity.noContent().build();



    }
}
