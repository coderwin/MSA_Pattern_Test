package com.example.orderservice.u_hexagonal.application.port.out;

import com.example.orderservice.u_hexagonal.common.OutPort;
import com.example.orderservice.u_hexagonal.domain.vo.OrderProductSaveDomain;

import java.util.List;

@OutPort
public interface KafkaEventOrderSaveOutPort {


    void orderEvent(List<OrderProductSaveDomain> orderProductSaveDomainList);
}
