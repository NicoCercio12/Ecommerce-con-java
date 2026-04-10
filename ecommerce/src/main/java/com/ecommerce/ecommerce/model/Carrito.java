package com.ecommerce.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrito", orphanRemoval = true)
    private List<CarritoItem> lstItems = new ArrayList<>();

    public Carrito(Long id, Usuario usuario, List<CarritoItem> lstItems) {
        this.id = id;
        this.usuario = usuario;
        this.lstItems = lstItems;
    }

    public Carrito() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<CarritoItem> getLstItems() {
        return lstItems;
    }

     public void setLstItems(List<CarritoItem> lstItems) {
        this.lstItems = lstItems;
    }


    @Override
    public String toString() {
        return "Carrito [id=" + id + ", usuario=" + usuario + ", lstItems=" + lstItems + "]";
    }

   
    

    
    
}
