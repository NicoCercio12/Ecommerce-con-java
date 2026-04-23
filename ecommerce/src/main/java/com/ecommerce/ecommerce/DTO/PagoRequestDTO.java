package com.ecommerce.ecommerce.DTO;

public class PagoRequestDTO {

    private Long ordenId;
    private String metodo;

    public PagoRequestDTO(Long ordenId, String metodo) {
        this.ordenId = ordenId;
        this.metodo = metodo;
    }

    public PagoRequestDTO(){}

    public Long getOrdenId() {
        return ordenId;
    }
    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }
    public String getMetodo() {
        return metodo;
    }
    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    
    
}
