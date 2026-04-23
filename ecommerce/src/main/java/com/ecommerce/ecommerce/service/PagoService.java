package com.ecommerce.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.DTO.PagoRequestDTO;
import com.ecommerce.ecommerce.DTO.PagoResponseDTO;
import com.ecommerce.ecommerce.mapper.PagoMapper;
import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.Pago;
import com.ecommerce.ecommerce.repository.OrdenRepository;
import com.ecommerce.ecommerce.repository.PagoRepository;

@Service
public class PagoService {

    private final PagoRepository repoPago;
    private final OrdenRepository repoOrden;

    public PagoService(PagoRepository repoPago, OrdenRepository repoOrden) {
        this.repoPago = repoPago;
        this.repoOrden = repoOrden;
    }

    public PagoResponseDTO procesarPago(PagoRequestDTO dto) {

        Orden orden = repoOrden.findById(dto.getOrdenId())
                .orElseThrow(() -> new RuntimeException("La orden no existe"));

        if (!orden.getEstado().equalsIgnoreCase("Pendiente")) {

            throw new RuntimeException("La orden ya fue procesada");

        }

        Pago pago = new Pago();
        pago.setOrden(orden);
        pago.setMonto(orden.getTotal());
        pago.setMetodo(dto.getMetodo());

        if (dto.getMetodo().equalsIgnoreCase("Tarjeta")) {
            pago.setEstado("APROBADO");
            orden.setEstado("PAGADA");
        }

        else {
            pago.setEstado("RECHAZADO");
            orden.setEstado("CANCELADA");
        }

        repoOrden.save(orden);
        Pago guardado = repoPago.save(pago);

        return PagoMapper.tDto(guardado);

    }

}
