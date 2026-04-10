package com.ecommerce.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.ecommerce.ecommerce.DTO.ProductoRequestDTO;
import com.ecommerce.ecommerce.DTO.ProductoResponseDTO;
import com.ecommerce.ecommerce.service.ProductoService;

@Controller("productos")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {

        service.eliminarProducto(id);

        return ResponseEntity.noContent().build();



    }
}
