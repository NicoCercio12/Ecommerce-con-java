package com.ecommerce.ecommerce.DTO;

import java.util.List;

public class CarritoResponseDTO {

    private Long id;
    private Long idUsuario;
    private List<ItemCarritoDTO> lstItems;
   
    public CarritoResponseDTO(Long id, Long idUsuario, List<ItemCarritoDTO> lstItems) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.lstItems = lstItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<ItemCarritoDTO> getLstItems() {
        return lstItems;
    }

    public void setLstItems(List<ItemCarritoDTO> lstItems) {
        this.lstItems = lstItems;
    }

    
    
    
}
