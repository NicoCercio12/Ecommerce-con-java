package com.ecommerce.ecommerce.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.ecommerce.ecommerce.model.*;
import com.ecommerce.ecommerce.repository.*;

class OrdenServiceTest {

    @Mock
    private OrdenRepository repoOrden;

    @Mock
    private CarritoRepository repoCarrito;

    @InjectMocks
    private OrdenService ordenService;

    private Carrito carrito;
    private Usuario usuario;
    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1L);

        producto = new Producto();
        producto.setId(10L);
        producto.setPrecio(100.0);

        CarritoItem item = new CarritoItem();
        item.setId(1L);
        item.setProducto(producto);
        item.setCantidad(2);

        carrito = new Carrito();
        carrito.setId(1L);
        carrito.setUsuario(usuario);
        carrito.setLstItems(new ArrayList<>());
        carrito.getLstItems().add(item);
    }

    // ✅ CREAR ORDEN OK
    @Test
    void agregarOrden_ok() {

        when(repoCarrito.findById(1L)).thenReturn(Optional.of(carrito));
        when(repoOrden.save(any(Orden.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        var response = ordenService.agregarOrden(1L);

        assertNotNull(response);
        assertEquals(200.0, response.getTotal()); // 2 * 100

        verify(repoOrden, times(1)).save(any(Orden.class));
        verify(repoCarrito, times(1)).save(carrito);

        // carrito se vacía después de comprar
        assertTrue(carrito.getLstItems().isEmpty());
    }

    // ❌ CARRITO NO EXISTE
    @Test
    void agregarOrden_carritoNoExiste() {

        when(repoCarrito.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            ordenService.agregarOrden(1L);
        });

        verify(repoOrden, never()).save(any());
    }

    // ❌ CARRITO VACÍO
    @Test
    void agregarOrden_carritoVacio() {

        carrito.setLstItems(new ArrayList<>());

        when(repoCarrito.findById(1L)).thenReturn(Optional.of(carrito));

        assertThrows(RuntimeException.class, () -> {
            ordenService.agregarOrden(1L);
        });

        verify(repoOrden, never()).save(any());
    }
}