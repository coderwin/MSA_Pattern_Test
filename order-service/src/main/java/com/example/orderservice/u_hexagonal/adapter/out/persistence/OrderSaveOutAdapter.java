package com.example.orderservice.u_hexagonal.adapter.out.persistence;

import com.example.orderservice.u_hexagonal.adapter.out.persistence.entity.OrderProduct;
import com.example.orderservice.u_hexagonal.adapter.out.persistence.entity.Orders;
import com.example.orderservice.u_hexagonal.adapter.out.persistence.mapper.OrderMapper;
import com.example.orderservice.u_hexagonal.adapter.out.persistence.repository.OrdersRepository;
import com.example.orderservice.u_hexagonal.application.port.out.OrderSaveOutPort;
import com.example.orderservice.u_hexagonal.common.OutAdapter;
import com.example.orderservice.u_hexagonal.domain.vo.OrderSaveDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
@OutAdapter
public class OrderSaveOutAdapter implements OrderSaveOutPort {

    private final OrdersRepository orderRepository;
    private final OrderMapper orderMapper;// domain과 eneity로 변환

    @Override
    public void save(OrderSaveDomain orderSaveDomain) {

        // domain을 OrderProduct entity로 변환하기
        List<OrderProduct> orderProductList = orderSaveDomain.getOrderProductSaveDomainList().stream()
                .map(orderMapper::orderProductSaveDomainToOrderProduct)
                .collect(toList());

        // domain을 Orders entity로 변환하기
        Orders orders = orderMapper.orderSaveDomainToOrders(orderSaveDomain);

        // 양방향 관계 매핑해준다.
        orderProductList.forEach(orders::addOrderProduct);

        // 저장하기
        orderRepository.save(orders);

    }
}
