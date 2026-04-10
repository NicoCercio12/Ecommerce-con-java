package com.ecommerce.ecommerce.service;

import org.springframework.stereotype.Service;
import com.ecommerce.ecommerce.DTO.CarritoRequestDTO;
import com.ecommerce.ecommerce.DTO.CarritoResponseDTO;
import com.ecommerce.ecommerce.mapper.CarritoMapper;
import com.ecommerce.ecommerce.model.Carrito;
import com.ecommerce.ecommerce.model.CarritoItem;
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.repository.CarritoRepository;
import com.ecommerce.ecommerce.repository.ProductoRepository;
import com.ecommerce.ecommerce.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarritoService {

    private final CarritoRepository repoCarrito;
    private final ProductoRepository repoProducto;
    private final UsuarioRepository repoUsuario;

    public CarritoService(CarritoRepository repoCarrito, ProductoRepository repoProducto,
        UsuarioRepository repoUsuario) { 
            
        this.repoCarrito = repoCarrito;
        this.repoProducto = repoProducto;
        this.repoUsuario = repoUsuario;
    }

    // Agregar Producto al carrito

    public CarritoResponseDTO agregarProductoCarrito(CarritoRequestDTO dto) {

        Usuario usuario = repoUsuario.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        Producto producto = repoProducto.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("El producto no existe"));

        // Buscar el carrito del usuario o crearlo

        Carrito carrito = repoCarrito.findAll()
                .stream()
                .filter(c -> c.getUsuario().getId().equals(usuario.getId()))
                .findFirst()
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setUsuario(usuario);
                    return nuevo;
                });

        // Verifico que el producto exista en el carrito

        CarritoItem itemExistente = carrito.getLstItems().stream()
                .filter(i -> i.getProducto().getId().equals(producto.getId()))
                .findFirst()
                .orElse(null);

        if (itemExistente != null) {
            itemExistente.setCantidad(itemExistente.getCantidad() + dto.getCantidad());
        }

        else {

            CarritoItem item = new CarritoItem();
            item.setProducto(producto);
            item.setCantidad(dto.getCantidad());
            item.setCarrito(carrito);
            carrito.getLstItems().add(item);

        }

        Carrito guardado = repoCarrito.save(carrito);

        return CarritoMapper.toDTO(guardado);

    }

    // traer carrito por usuario

    public CarritoResponseDTO traerCarritoPorUsuario(Long usuarioId) {

        Carrito carrito = repoCarrito.findAll().stream()
                .filter(c -> c.getUsuario().getId().equals(usuarioId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        return CarritoMapper.toDTO(carrito);

    }

    // Vaciar carrito

    public void vaciarCarrito(Long carritoId) {

        Carrito carrito = repoCarrito.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("El carrito no existe"));

        carrito.getLstItems().clear();

        repoCarrito.save(carrito);
    }

    // Eliminar producto del carrito

    public void eliminarProductoCarrito(Long carritoId, Long itemId) {

        Carrito carrito = repoCarrito.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("El carrito no existe"));

        CarritoItem item = carrito.getLstItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El producto no existe en el carrito"));

        carrito.getLstItems().remove(item);

        repoCarrito.save(carrito);

    }
}
