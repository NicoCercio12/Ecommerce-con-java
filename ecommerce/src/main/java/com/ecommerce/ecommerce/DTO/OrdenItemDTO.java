package com.ecommerce.ecommerce.DTO;

public class OrdenItemDTO {

    private Long id;
    private Long productoId;
    private Integer cantidad;
    private double precioUnitario;

    public OrdenItemDTO() {
    }

    public OrdenItemDTO(Long id, Long productoId, Integer cantidad, double precioUnitario) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    


}
