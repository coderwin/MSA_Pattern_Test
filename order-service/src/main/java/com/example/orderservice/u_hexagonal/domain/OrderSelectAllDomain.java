package com.example.orderservice.u_hexagonal.domain;

import com.example.orderservice.domain.OrderDetailResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderSelectAllDomain {

    private Long orderId;
    private String addr; //배송주소
    private Long customerId;
    private Date orderDate;
//    private List<OrderDetailResponseDTO> orderproductlist;
    private List<OrderProductSelectAllDomain> orderProductSelectAllDomainList;

}
