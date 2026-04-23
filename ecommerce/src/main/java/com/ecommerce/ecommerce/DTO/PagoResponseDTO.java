package com.ecommerce.ecommerce.DTO;

public class PagoResponseDTO {
    
    private Long id;
    private double monto;
    private String metodo;
    private String estado;
    private Long ordenId;
    
    public PagoResponseDTO(Long id, double monto, String metodo, String estado, Long ordenId) {
        this.id = id;
        this.monto = monto;
        this.metodo = metodo;
        this.estado = estado;
        this.ordenId = ordenId;
    }

    public PagoResponseDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    
}
