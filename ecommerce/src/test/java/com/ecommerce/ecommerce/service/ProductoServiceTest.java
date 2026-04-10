package com.ecommerce.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.ecommerce.DTO.ProductoRequestDTO;
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.repository.ProductoRepository;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository repoProducto;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Notebook");
        producto.setSku("ABC123");
        producto.setPrecio(1000.0);
    }

    @Test
    void agregarProducto_ok() {

        ProductoRequestDTO dto = new ProductoRequestDTO(
                "Notebook",
                "Notebook gamer", // ✅ descripcion
                1000.0,
                10,
                "Fisico",
                "ABC123" // ✅ SKU
        );

        when(repoProducto.existsBySku("ABC123")).thenReturn(false);
        when(repoProducto.save(any(Producto.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        var response = productoService.agregarProducto(dto);

        assertNotNull(response);
        verify(repoProducto, times(1)).save(any(Producto.class));
    }

    @Test
    void agregarProducto_yaExiste() {

        ProductoRequestDTO dto = new ProductoRequestDTO(
                "Notebook",
                "Notebook gamer",
                1000.0,
                10,
                "Fisico",
                "ABC123");

        when(repoProducto.existsBySku("ABC123")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> {
            productoService.agregarProducto(dto);
        });

        verify(repoProducto, never()).save(any());
    }

    // ✅ TRAER PRODUCTO POR ID
    @Test
    void traerProductoPorId_ok() {

        when(repoProducto.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = productoService.traerProductoPorId(1L);

        assertNotNull(resultado);
        assertEquals("Notebook", resultado.getNombre());
    }

    // ❌ PRODUCTO NO EXISTE
    @Test
    void traerProductoPorId_noExiste() {

        when(repoProducto.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            productoService.traerProductoPorId(1L);
        });
    }

    // ✅ TRAER TODOS
    @Test
    void traerProductos_ok() {

        when(repoProducto.findAll()).thenReturn(List.of(producto));

        List<Producto> lista = productoService.traerProductos();

        assertEquals(1, lista.size());
    }

    // ✅ MODIFICAR PRODUCTO
    @Test
    void modificarProducto_ok() {

        ProductoRequestDTO dto = new ProductoRequestDTO("Notebook Pro", "Aaaaaa", 1500.0, 10, "Fisico", "ABC-EFG-125");

        when(repoProducto.findById(1L)).thenReturn(Optional.of(producto));
        when(repoProducto.save(any(Producto.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        var response = productoService.modificarProducto(1L, dto);

        assertNotNull(response);
        verify(repoProducto, times(1)).save(any(Producto.class));
    }

    // ❌ MODIFICAR PRODUCTO NO EXISTE
    @Test
    void modificarProducto_noExiste() {

        ProductoRequestDTO dto = new ProductoRequestDTO("Notebook Pro", "ABC123", 1500.0, 20, "Digital", "fskdhfksd");

        when(repoProducto.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            productoService.modificarProducto(1L, dto);
        });
    }

    // ✅ ELIMINAR PRODUCTO
    @Test
    void eliminarProducto_ok() {

        when(repoProducto.findById(1L)).thenReturn(Optional.of(producto));

        productoService.eliminarProducto(1L);

        verify(repoProducto, times(1)).delete(producto);
    }

    // ❌ ELIMINAR PRODUCTO NO EXISTE
    @Test
    void eliminarProducto_noExiste() {

        when(repoProducto.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            productoService.eliminarProducto(1L);
        });

        verify(repoProducto, never()).delete(any());
    }
}
