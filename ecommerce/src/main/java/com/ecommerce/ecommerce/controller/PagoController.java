package com.ecommerce.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.DTO.PagoRequestDTO;
import com.ecommerce.ecommerce.DTO.PagoResponseDTO;
import com.ecommerce.ecommerce.service.PagoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/pagos")
public class PagoController {
    
    private final PagoService service;

    public PagoController(PagoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PagoResponseDTO> pagar(@RequestBody PagoRequestDTO dto) {

        PagoResponseDTO pago = service.procesarPago(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(pago);

    }


}
