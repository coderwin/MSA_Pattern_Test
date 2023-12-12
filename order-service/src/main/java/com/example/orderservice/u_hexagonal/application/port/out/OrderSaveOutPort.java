package com.example.orderservice.u_hexagonal.application.port.out;

import com.example.orderservice.u_hexagonal.common.OutPort;
import com.example.orderservice.u_hexagonal.domain.vo.OrderSaveDomain;

@OutPort
public interface OrderSaveOutPort {
    void save(OrderSaveDomain orderSaveDomain);
}
