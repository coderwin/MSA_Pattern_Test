package com.example.productservice.u_hexagonal.application.port.in;

import com.example.productservice.u_hexagonal.adapter.in.dto.KafkaOrderProductSaveRequestDTO;
import com.example.productservice.u_hexagonal.common.InPort;

import java.util.List;

@InPort
public interface KafkaProductStockUpdateInPort {
    void subtractStock(List<KafkaOrderProductSaveRequestDTO> request);
}
