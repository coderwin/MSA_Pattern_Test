package com.example.orderservice.u_hexagonal.adapter.out.persistence.repository;

import com.example.orderservice.domain.OrderEntity;
import com.example.orderservice.u_hexagonal.adapter.out.persistence.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomerId(Long customerId);
}
