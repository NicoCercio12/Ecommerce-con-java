package com.ecommerce.ecommerce.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.ecommerce.ecommerce.DTO.PagoRequestDTO;
import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.Pago;
import com.ecommerce.ecommerce.repository.OrdenRepository;
import com.ecommerce.ecommerce.repository.PagoRepository;

class PagoServiceTest {

    @Mock
    private PagoRepository repoPago;

    @Mock
    private OrdenRepository repoOrden;

    @InjectMocks
    private PagoService pagoService;

    private Orden orden;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        orden = new Orden();
        orden.setId(1L);
        orden.setEstado("PENDIENTE");
        orden.setTotal(1000.0);
    }

    // ✅ PAGO OK
    @Test
    void procesarPago_ok() {

        PagoRequestDTO dto = new PagoRequestDTO(1L, "TARJETA");

        when(repoOrden.findById(1L)).thenReturn(Optional.of(orden));
        when(repoPago.save(any(Pago.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        var response = pagoService.procesarPago(dto);

        assertNotNull(response);
        assertEquals("APROBADO", response.getEstado());

        verify(repoOrden).save(any(Orden.class));
        verify(repoPago).save(any(Pago.class));
    }

    // ❌ ORDEN NO EXISTE
    @Test
    void procesarPago_ordenNoExiste() {

        PagoRequestDTO dto = new PagoRequestDTO(1L, "TARJETA");

        when(repoOrden.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            pagoService.procesarPago(dto);
        });

        verify(repoPago, never()).save(any());
    }

    // ❌ ORDEN YA PROCESADA
    @Test
    void procesarPago_ordenYaProcesada() {

        orden.setEstado("PAGADA");

        PagoRequestDTO dto = new PagoRequestDTO(1L, "TARJETA");

        when(repoOrden.findById(1L)).thenReturn(Optional.of(orden));

        assertThrows(RuntimeException.class, () -> {
            pagoService.procesarPago(dto);
        });

        verify(repoPago, never()).save(any());
    }

    // ❌ PAGO RECHAZADO
    @Test
    void procesarPago_rechazado() {

        PagoRequestDTO dto = new PagoRequestDTO(1L, "EFECTIVO");

        when(repoOrden.findById(1L)).thenReturn(Optional.of(orden));
        when(repoPago.save(any(Pago.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        var response = pagoService.procesarPago(dto);

        assertEquals("RECHAZADO", response.getEstado());
        assertEquals("CANCELADA", orden.getEstado());

        verify(repoPago).save(any(Pago.class));
    }
}