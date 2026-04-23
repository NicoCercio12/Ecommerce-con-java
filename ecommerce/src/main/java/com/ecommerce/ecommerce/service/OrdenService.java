package com.ecommerce.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.DTO.OrdenResponseDTO;
import com.ecommerce.ecommerce.mapper.OrdenMapper;
import com.ecommerce.ecommerce.model.Carrito;
import com.ecommerce.ecommerce.model.CarritoItem;
import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.OrdenItem;
import com.ecommerce.ecommerce.repository.CarritoRepository;
import com.ecommerce.ecommerce.repository.OrdenRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrdenService {
    
    private final OrdenRepository repoOrden;
    private final CarritoRepository repoCarrito;
    
    public OrdenService(OrdenRepository repoOrden, CarritoRepository repoCarrito) {
        this.repoOrden = repoOrden;
        this.repoCarrito = repoCarrito;
    }

    public OrdenResponseDTO agregarOrden(Long carritoId) {

        Carrito carrito = repoCarrito.findById(carritoId).orElseThrow(() -> new RuntimeException("El carrito no existe"));

        if(carrito.getLstItems().isEmpty()) {

            throw new RuntimeException("El carrito esta vacio");

        }

        Orden orden = new Orden();
        orden.setUsuario(carrito.getUsuario());
        orden.setFecha(LocalDateTime.now());
        orden.setEstado("PENDIENTE");

        List<OrdenItem> items = new ArrayList<>();
        double total = 0.0;

        for(CarritoItem item : carrito.getLstItems()) {

            OrdenItem ordenItem = new OrdenItem();
            ordenItem.setOrden(orden);
            ordenItem.setProducto(item.getProducto());
            ordenItem.setCantidad(item.getCantidad());
            ordenItem.setPrecioUnitario(item.getProducto().getPrecio());

            total += item.getCantidad() * item.getProducto().getPrecio();

            items.add(ordenItem);

        }

        orden.setLstItems(items);
        orden.setTotal(total);

        Orden guardada = repoOrden.save(orden);

        carrito.getLstItems().clear();
        repoCarrito.save(carrito);

        return OrdenMapper.toDTO(guardada);

    }

    

}
