package com.example.productservice.u_hexagonal.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaOrderProductSaveDomain {

    private Long productNo;
    private int count;//주문수량
}
