package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Long>{

    
}
