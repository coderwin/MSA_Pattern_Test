package com.example.orderservice.u_hexagonal.application.port.out;

import com.example.orderservice.u_hexagonal.common.OutPort;
import com.example.orderservice.u_hexagonal.domain.OrderSelectAllDomain;

import java.util.List;

@OutPort
public interface OrderSelectAllOutPort {
    List<OrderSelectAllDomain> getOrderSelectAllDomain(Long customerId);
}
