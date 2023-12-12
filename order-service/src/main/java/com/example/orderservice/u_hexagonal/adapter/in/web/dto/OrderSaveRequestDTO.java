package com.example.orderservice.u_hexagonal.adapter.in.web.dto;

import com.example.orderservice.domain.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaveRequestDTO {
    private String addr; //배송주소
    private Long customerId;
    private List<OrderProductSaveRequestDTO> OrderProductSaveRequestDtoList;
}
