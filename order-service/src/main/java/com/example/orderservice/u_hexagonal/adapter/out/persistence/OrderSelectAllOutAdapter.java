package com.example.orderservice.u_hexagonal.adapter.out.persistence;

import com.example.orderservice.domain.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.u_hexagonal.adapter.out.persistence.entity.Orders;
import com.example.orderservice.u_hexagonal.adapter.out.persistence.mapper.OrderMapper;
import com.example.orderservice.u_hexagonal.adapter.out.persistence.repository.OrdersRepository;
import com.example.orderservice.u_hexagonal.application.port.out.OrderSelectAllOutPort;
import com.example.orderservice.u_hexagonal.common.OutAdapter;
import com.example.orderservice.u_hexagonal.domain.OrderProductSelectAllDomain;
import com.example.orderservice.u_hexagonal.domain.OrderSelectAllDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
@Slf4j
@OutAdapter
public class OrderSelectAllOutAdapter implements OrderSelectAllOutPort {

    private final OrdersRepository ordersRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderSelectAllDomain> getOrderSelectAllDomain(Long customerId) {

        // db에서 ordre 주문 list 불러오기
        List<Orders> orderlist =  ordersRepository.findByCustomerId(customerId);

        log.info("조회결과=>{}",orderlist);
        log.info("상세조회결과=>{}",orderlist.get(0).getOrderProductList());

        // entity -> domain으로 변경하기
        List<OrderSelectAllDomain> orderSelectAllDomainList = orderlist.stream()
                .map((data) -> {
                    // OrderSelectAllDomain 생성
                    OrderSelectAllDomain orderSelectAllDomain = orderMapper.ordersToOrderSelectAllDomain(data);
                    // OrderProductSelectAllDomain List 첨가
                    List<OrderProductSelectAllDomain> orderProductSelectAllDomainList = data.getOrderProductList().stream()
                            .map(orderMapper::orderProductToOrderProductSelectAllDomain)
                            .collect(toList());
                    orderSelectAllDomain.setOrderProductSelectAllDomainList(orderProductSelectAllDomainList);

                    return orderSelectAllDomain;
                })
                .collect(toList());

        return orderSelectAllDomainList;
    }
}
