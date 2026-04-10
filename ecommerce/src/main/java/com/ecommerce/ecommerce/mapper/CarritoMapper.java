package com.ecommerce.ecommerce.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.ecommerce.ecommerce.DTO.CarritoResponseDTO;
import com.ecommerce.ecommerce.DTO.ItemCarritoDTO;
import com.ecommerce.ecommerce.model.Carrito;
import com.ecommerce.ecommerce.model.CarritoItem;
import com.ecommerce.ecommerce.model.Producto;

public class CarritoMapper {

    public static CarritoItem toEntity(ItemCarritoDTO dto, Producto producto, Carrito carrito) {

        CarritoItem item = new CarritoItem();
        item.setProducto(producto);
        item.setCantidad(dto.getCantidad());
        item.setCarrito(carrito);
        
        return item;

    }

    public static ItemCarritoDTO toDto(CarritoItem item) {

        return new ItemCarritoDTO(item.getId(), item.getProducto().getId(), item.getCantidad());

    }

    public static CarritoResponseDTO toDTO(Carrito carrito) {

        List<ItemCarritoDTO> items = carrito.getLstItems().stream().map(CarritoMapper::toDto).collect(Collectors.toList());

        return new CarritoResponseDTO(carrito.getId(), carrito.getUsuario().getId(), items);
    }


    
}
