package com.example.orderservice.u_hexagonal.application.port.in;

import com.example.orderservice.u_hexagonal.adapter.in.web.dto.OrderSelectAllResponseDTO;
import com.example.orderservice.u_hexagonal.common.InPort;

import java.util.List;

@InPort
public interface OrderSelectAllInPort {
    List<OrderSelectAllResponseDTO> findAllByCustomerId(Long customerId);
}
