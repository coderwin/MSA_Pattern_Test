package com.example.orderservice.u_hexagonal.domain.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 기능 : OrderProduct Save domain
 */
@Getter
@Setter
public class OrderProductSaveDomain {

    private Long productNo;
    private int orderPrice;//구매가격
    private int count;//주문수량

}
