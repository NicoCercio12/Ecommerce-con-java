package com.ecommerce.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.ecommerce.DTO.OrdenResponseDTO;
import com.ecommerce.ecommerce.service.OrdenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {
    
    private final OrdenService service;

    public OrdenController(OrdenService service) {
        this.service = service;
    }

    @PostMapping("/{carritoId")
    public ResponseEntity<OrdenResponseDTO> agregarOrden(@PathVariable Long carritoId) {

        OrdenResponseDTO orden = service.agregarOrden(carritoId);

        return ResponseEntity.status(HttpStatus.CREATED).body(orden);

    }

    

    
    

}
