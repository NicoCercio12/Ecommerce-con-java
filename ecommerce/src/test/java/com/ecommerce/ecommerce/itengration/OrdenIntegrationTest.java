package com.ecommerce.ecommerce.itengration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.model.*;
import com.ecommerce.ecommerce.repository.*;
import com.ecommerce.ecommerce.service.OrdenService;

@SpringBootTest
@Transactional
class OrdenIntegrationTest {

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private CarritoRepository repoCarrito;

    @Autowired
    private ProductoRepository repoProducto;

    @Autowired
    private UsuarioRepository repoUsuario;

    @Autowired
    private OrdenRepository repoOrden;

    @Test
    void flujoCompleto_crearOrden() {

        // 🧱 1. Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombre("Nico");
        usuario = repoUsuario.save(usuario);

        // 🧱 2. Crear producto
        Producto producto = new Producto();
        producto.setNombre("Notebook");
        producto.setPrecio(100.0);
        producto.setStock(10);
        producto.setSku("ABC123");
        producto = repoProducto.save(producto);

        // 🧱 3. Crear carrito
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setLstItems(new ArrayList<>());

        // 🧱 4. Agregar item al carrito
        CarritoItem item = new CarritoItem();
        item.setProducto(producto);
        item.setCantidad(2);
        item.setCarrito(carrito);

        carrito.getLstItems().add(item);
        carrito = repoCarrito.save(carrito);

        // 🚀 5. Ejecutar lógica real
        var ordenResponse = ordenService.agregarOrden(carrito.getId());

        // ✅ 6. Validaciones
        assertNotNull(ordenResponse);
        assertEquals(200.0, ordenResponse.getTotal());
        assertEquals("PENDIENTE", ordenResponse.getEstado());

        // carrito debería quedar vacío
        Carrito carritoActualizado = repoCarrito.findById(carrito.getId()).get();
        assertTrue(carritoActualizado.getLstItems().isEmpty());

        // orden guardada en DB
        assertEquals(1, repoOrden.findAll().size());
    }
}