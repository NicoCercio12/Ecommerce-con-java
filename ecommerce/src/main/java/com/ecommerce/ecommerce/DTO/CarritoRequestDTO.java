package com.ecommerce.ecommerce.DTO;

public class CarritoRequestDTO {

    private Long usuarioId;
    private Long productoId;
    private Integer cantidad;
    
    public CarritoRequestDTO(Long usuarioId, Long productoId, Integer cantidad) {
        this.usuarioId = usuarioId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public CarritoRequestDTO() {}

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    
    
    
}
