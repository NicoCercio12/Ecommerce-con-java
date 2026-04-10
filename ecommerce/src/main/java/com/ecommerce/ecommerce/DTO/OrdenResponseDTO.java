package com.ecommerce.ecommerce.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdenResponseDTO {

    private Long id;
    private Long usuarioId;
    private List<OrdenItemDTO> items = new ArrayList<>();
    private double total;
    private String estado;
    private LocalDateTime fecha;

    public OrdenResponseDTO() {}

    public OrdenResponseDTO(Long id, Long usuarioId, List<OrdenItemDTO> items, double total, String estado,
            LocalDateTime fecha) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.items = items;
        this.total = total;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<OrdenItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrdenItemDTO> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    


  
}