package com.ecommerce.ecommerce.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce.DTO.OrdenItemDTO;
import com.ecommerce.ecommerce.DTO.OrdenResponseDTO;
import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.OrdenItem;

public class OrdenMapper {

    public static OrdenItemDTO toDTO(OrdenItem item) {

        return new OrdenItemDTO(

                item.getId(),
                item.getProducto().getId(),
                item.getCantidad(),
                item.getPrecioUnitario()

        );
    }

    public static OrdenResponseDTO toDTO(Orden orden) {

        List<OrdenItemDTO> items = orden.getLstItems().stream()
                .map(OrdenMapper::toDTO)
                .collect(Collectors.toList());

        return new OrdenResponseDTO(

                orden.getId(),
                orden.getUsuario().getId(),
                items,
                orden.getTotal(),
                orden.getEstado(),
                orden.getFecha()

        );

    }
}
