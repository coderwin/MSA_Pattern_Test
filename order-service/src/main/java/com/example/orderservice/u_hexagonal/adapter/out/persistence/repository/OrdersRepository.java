package com.example.orderservice.u_hexagonal.adapter.out.persistence.repository;

import com.example.orderservice.u_hexagonal.adapter.out.persistence.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
