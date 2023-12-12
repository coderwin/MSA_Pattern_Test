package com.example.orderservice.u_hexagonal.adapter.out.kafka.dto;

import lombok.Getter;

@Getter
public class KafkaOrderProductSaveRequestDTO {

    private Long productNo;
    private int orderPrice;//구매가격
    private int count;//주문수량
}
