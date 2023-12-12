package com.example.orderservice.u_hexagonal.domain;

import com.example.orderservice.domain.OrderDetailResponseDTO;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class OrderProductSelectAllDomain {

    private Long orderDetailId;
    private Long productNo;
    private int orderPrice;//구매가격
    private int count;//주문수량
}
