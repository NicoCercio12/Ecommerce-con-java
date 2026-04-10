package com.ecommerce.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.DTO.ProductoRequestDTO;
import com.ecommerce.ecommerce.DTO.ProductoResponseDTO;
import com.ecommerce.ecommerce.mapper.ProductoMapper;
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository repoProducto;

    public ProductoService(ProductoRepository repoProducto) {
        this.repoProducto = repoProducto;
    }

    public ProductoResponseDTO agregarProducto(ProductoRequestDTO dto) {

        if(repoProducto.existsBySku(dto.getSku())) {

            throw new RuntimeException("El producto ya existe");
        }

        Producto producto = ProductoMapper.toEntity(dto);
        Producto guardado = repoProducto.save(producto);

        return ProductoMapper.toDto(guardado);
    }

    public Producto traerProductoPorId(Long Id) {

        return repoProducto.findById(Id).orElseThrow(() -> new RuntimeException("No existe el producto"));

    }

    public List<Producto> traerProductos() {

        return repoProducto.findAll();
        
    }

    public ProductoResponseDTO modificarProducto(Long id, ProductoRequestDTO dto) {

        Producto productoModificar = repoProducto.findById(id).orElseThrow(() -> new RuntimeException("El producto no existe. Error al modificar"));

        ProductoMapper.updateEntity(productoModificar, dto);
        Producto actualizado = repoProducto.save(productoModificar);

        return ProductoMapper.toDto(actualizado);

    } 

    public void eliminarProducto(Long Id) {

        Producto productoEliminar = repoProducto.findById(Id).orElseThrow(() -> new RuntimeException("El producto no existe. Error al eliminar"));

        repoProducto.delete(productoEliminar);

    }
     
}
