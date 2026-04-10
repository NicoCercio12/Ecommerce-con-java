package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.DTO.ProductoRequestDTO;
import com.ecommerce.ecommerce.DTO.ProductoResponseDTO;
import com.ecommerce.ecommerce.model.Producto;

public class ProductoMapper {

    public static Producto toEntity(ProductoRequestDTO dto) {
        
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setTipo(dto.getTipo());
        producto.setSku(dto.getSku());

        return producto;

    }

    public static ProductoResponseDTO toDto(Producto producto) {

        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());
        response.setTipo(producto.getTipo());
        response.setSku(producto.getSku());

        return response;

    }

    public static void updateEntity(Producto producto, ProductoRequestDTO dto) {

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(producto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setTipo(dto.getTipo());
        producto.setSku(dto.getSku());

    }


    
}
