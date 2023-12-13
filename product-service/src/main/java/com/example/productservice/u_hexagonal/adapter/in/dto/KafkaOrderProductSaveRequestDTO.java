package com.example.productservice.u_hexagonal.adapter.in.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
//@ToString
public class KafkaOrderProductSaveRequestDTO {

    private Long productNo;
    private int orderPrice;//구매가격
    private int count;//주문수량
}
