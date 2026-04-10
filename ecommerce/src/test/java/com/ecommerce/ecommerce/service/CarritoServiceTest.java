package com.ecommerce.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.ecommerce.ecommerce.DTO.CarritoRequestDTO;
import com.ecommerce.ecommerce.model.Carrito;
import com.ecommerce.ecommerce.model.CarritoItem;
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.repository.CarritoRepository;
import com.ecommerce.ecommerce.repository.ProductoRepository;
import com.ecommerce.ecommerce.repository.UsuarioRepository;

public class CarritoServiceTest {

     @Mock
    private CarritoRepository repoCarrito;

    @Mock
    private ProductoRepository repoProducto;

    @Mock
    private UsuarioRepository repoUsuario;

    @InjectMocks
    private CarritoService carritoService;

    private Usuario usuario;
    private Producto producto;
    private Carrito carrito;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1L);

        producto = new Producto();
        producto.setId(10L);
        producto.setPrecio(100.0);

        carrito = new Carrito();
        carrito.setId(1L);
        carrito.setUsuario(usuario);
        carrito.setLstItems(new ArrayList<>());
    }

    // ✅ Test: agregar producto nuevo al carrito
   @Test
    void agregarProductoNuevo() {

        CarritoRequestDTO dto = new CarritoRequestDTO(1L, 10L, 2);

        when(repoUsuario.findById(1L)).thenReturn(Optional.of(usuario));
        when(repoProducto.findById(10L)).thenReturn(Optional.of(producto));
        when(repoCarrito.findAll()).thenReturn(new ArrayList<>());
        when(repoCarrito.save(any(Carrito.class))).thenReturn(carrito);

        var response = carritoService.agregarProductoCarrito(dto);

        assertNotNull(response);
        verify(repoCarrito, times(1)).save(any(Carrito.class));
    }
    
    // ✅ Test: agregar producto existente (suma cantidad)
    @Test
    void agregarProductoExistente() {

        CarritoItem item = new CarritoItem();
        item.setId(1L);
        item.setProducto(producto);
        item.setCantidad(2);

        carrito.getLstItems().add(item);

        CarritoRequestDTO dto = new CarritoRequestDTO(1L, 10L, 3);

        when(repoUsuario.findById(1L)).thenReturn(Optional.of(usuario));
        when(repoProducto.findById(10L)).thenReturn(Optional.of(producto));
        when(repoCarrito.findAll()).thenReturn(java.util.List.of(carrito));
        when(repoCarrito.save(any(Carrito.class))).thenReturn(carrito);

        carritoService.agregarProductoCarrito(dto);

        assertEquals(5, item.getCantidad()); // 2 + 3
    }

    // ✅ Test: usuario no existe
    @Test
    void usuarioNoExiste() {

        CarritoRequestDTO dto = new CarritoRequestDTO(1L, 10L, 2);

        when(repoUsuario.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            carritoService.agregarProductoCarrito(dto);
        });
    }

    // ✅ Test: traer carrito por usuario
    @Test
    void traerCarritoPorUsuario() {

        when(repoCarrito.findAll()).thenReturn(java.util.List.of(carrito));

        var response = carritoService.traerCarritoPorUsuario(1L);

        assertNotNull(response);
    }

    // ✅ Test: vaciar carrito
    @Test
    void vaciarCarrito() {

        carrito.getLstItems().add(new CarritoItem());

        when(repoCarrito.findById(1L)).thenReturn(Optional.of(carrito));

        carritoService.vaciarCarrito(1L);

        assertTrue(carrito.getLstItems().isEmpty());
    }

    // ✅ Test: eliminar producto del carrito
    @Test
    void eliminarProductoCarrito() {

        CarritoItem item = new CarritoItem();
        item.setId(1L);

        carrito.getLstItems().add(item);

        when(repoCarrito.findById(1L)).thenReturn(Optional.of(carrito));

        carritoService.eliminarProductoCarrito(1L, 1L);

        assertTrue(carrito.getLstItems().isEmpty());
    }
}
    

