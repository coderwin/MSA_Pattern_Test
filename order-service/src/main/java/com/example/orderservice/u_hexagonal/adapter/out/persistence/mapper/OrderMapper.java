package com.example.orderservice.u_hexagonal.adapter.out.persistence.mapper;

import com.example.orderservice.u_hexagonal.adapter.out.persistence.entity.OrderProduct;
import com.example.orderservice.u_hexagonal.adapter.out.persistence.entity.Orders;
import com.example.orderservice.u_hexagonal.domain.vo.OrderProductSaveDomain;
import com.example.orderservice.u_hexagonal.domain.vo.OrderSaveDomain;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 설명 : domain을 entity로 또는
 *       entity를 domain으로 변경
 */
@Component
public class OrderMapper {


    public OrderProduct orderProductSaveDomainToOrderProduct(OrderProductSaveDomain orderProductSaveDomain) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(orderProductSaveDomain, OrderProduct.class);
    }


    public Orders orderSaveDomainToOrders(OrderSaveDomain orderSaveDomain) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(orderSaveDomain, Orders.class);
    }
}
