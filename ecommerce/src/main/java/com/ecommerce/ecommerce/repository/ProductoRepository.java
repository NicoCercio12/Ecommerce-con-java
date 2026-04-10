package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    boolean existsBySku(String sku);
    
}
