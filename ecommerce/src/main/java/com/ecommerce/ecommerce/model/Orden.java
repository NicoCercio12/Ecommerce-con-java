package com.ecommerce.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    private double total;

    private String estado;

    private LocalDateTime fecha;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL) 
    private List<OrdenItem> lstItems = new ArrayList<>(); 

    public Orden(){}

    public Orden(Long id, Usuario usuario, double total, String estado, LocalDateTime fecha, List<OrdenItem> lstItems) {
        this.id = id;
        this.usuario = usuario;
        this.total = total;
        this.estado = estado;
        this.fecha = fecha;
        this.lstItems = lstItems;
    }

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

    public List<OrdenItem> getLstItems() {
        return lstItems;
    }

    public void setLstItems(List<OrdenItem> lstItems) {
        this.lstItems = lstItems;
    }

    
    
    
}
