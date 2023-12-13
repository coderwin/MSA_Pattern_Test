package com.example.productservice.u_hexagonal.application.port.out;

import com.example.productservice.u_hexagonal.common.OutPort;
import com.example.productservice.u_hexagonal.domain.vo.KafkaOrderProductSaveDomain;

import java.util.List;

@OutPort
public interface KafkaProductStockUpdateOutPort {
    void subtractStock(List<KafkaOrderProductSaveDomain> kafkaOrderProductSaveDomainList);
}
