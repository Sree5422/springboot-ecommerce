package com.ecommerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.product.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
