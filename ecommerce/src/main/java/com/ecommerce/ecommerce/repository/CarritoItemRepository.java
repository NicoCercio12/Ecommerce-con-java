package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.CarritoItem;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {

    
}
