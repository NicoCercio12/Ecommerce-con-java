package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.DTO.PagoResponseDTO;
import com.ecommerce.ecommerce.model.Pago;

public class PagoMapper {

    public static PagoResponseDTO tDto(Pago pago) {

        return new PagoResponseDTO(

            pago.getId(),
            pago.getMonto(),
            pago.getMetodo(),
            pago.getEstado(),
            pago.getOrden().getId()

        );

    }
    
}
