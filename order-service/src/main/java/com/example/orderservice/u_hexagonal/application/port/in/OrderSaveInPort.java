package com.example.orderservice.u_hexagonal.application.port.in;

import com.example.orderservice.u_hexagonal.adapter.in.web.dto.OrderSaveRequestDTO;
import com.example.orderservice.u_hexagonal.common.InPort;
import org.springframework.transaction.annotation.Transactional;

@InPort
public interface OrderSaveInPort {

    void save(OrderSaveRequestDTO orderRequest);
}
