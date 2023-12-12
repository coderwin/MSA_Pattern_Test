package com.example.orderservice.u_hexagonal.domain.vo;

import com.example.orderservice.u_hexagonal.adapter.in.web.dto.OrderProductSaveRequestDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class OrderSaveDomain {

    private String addr; //배송주소
    private Long customerId;
    private List<OrderProductSaveDomain> orderProductSaveDomainList;

    @Builder
    public OrderSaveDomain(String addr, Long customerId, List<OrderProductSaveDomain> orderProductSaveDomainList) {
        this.addr = addr;
        this.customerId = customerId;
        this.orderProductSaveDomainList = orderProductSaveDomainList;
    }

    // 생성 메서드
    public static OrderSaveDomain makeOrderSaveDomain(String addr, Long customerId, List<OrderProductSaveDomain> orderProductSaveDomainList) {

        return OrderSaveDomain.builder()
                .addr(addr)
                .customerId(customerId)
                .orderProductSaveDomainList(orderProductSaveDomainList)
                .build();

    }
}
