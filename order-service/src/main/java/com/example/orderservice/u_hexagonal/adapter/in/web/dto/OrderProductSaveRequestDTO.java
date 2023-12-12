package com.example.orderservice.u_hexagonal.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductSaveRequestDTO {

    private Long productNo;
    private int orderPrice;//구매가격
    private int count;//주문수량
}
