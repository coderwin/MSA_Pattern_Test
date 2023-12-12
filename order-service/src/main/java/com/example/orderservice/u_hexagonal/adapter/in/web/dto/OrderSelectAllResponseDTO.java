package com.example.orderservice.u_hexagonal.adapter.in.web.dto;

import com.example.orderservice.domain.OrderDetailResponseDTO;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class OrderSelectAllResponseDTO {

    private Long orderId;
    private String addr; //배송주소
    private Long customerId;
    private Date orderDate;
    private List<OrderProductSelectAllResponseDTO> OrderProductSelectAllResponseDtoList;
}
