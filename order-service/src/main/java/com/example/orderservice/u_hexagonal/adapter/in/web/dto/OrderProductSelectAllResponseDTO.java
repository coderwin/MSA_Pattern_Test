package com.example.orderservice.u_hexagonal.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class OrderProductSelectAllResponseDTO {
    private Long orderDetailId;
    private Long productNo;
    private int orderPrice;//구매가격
    private int count;//주문수량
}
