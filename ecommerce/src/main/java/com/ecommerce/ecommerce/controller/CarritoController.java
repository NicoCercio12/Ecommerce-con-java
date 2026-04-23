package com.ecommerce.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.ecommerce.DTO.CarritoRequestDTO;
import com.ecommerce.ecommerce.DTO.CarritoResponseDTO;
import com.ecommerce.ecommerce.service.CarritoService;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {
    
    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CarritoResponseDTO> agregarProductoAlCarrito(@RequestBody CarritoRequestDTO dto) {

        return ResponseEntity.ok(service.agregarProductoCarrito(dto));

    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<CarritoResponseDTO> traerCarrito(@PathVariable Long usuarioId) {

        return ResponseEntity.ok(service.traerCarritoPorUsuario(usuarioId));

    }

    @DeleteMapping("/{carritoId}/item({itemId}")
    public ResponseEntity<CarritoResponseDTO> eliminarProducto(@PathVariable Long carritoId, @PathVariable Long itemId) {

        service.eliminarProductoCarrito(carritoId, itemId);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{carritoId}")
    public ResponseEntity<CarritoResponseDTO> vaciarCarrito(@PathVariable Long carritoId) {

        service.vaciarCarrito(carritoId);

        return ResponseEntity.noContent().build();

    }
}
