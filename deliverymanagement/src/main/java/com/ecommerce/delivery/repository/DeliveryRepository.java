package com.ecommerce.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.delivery.model.Delivery;

@Repository

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
