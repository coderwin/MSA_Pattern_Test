package com.example.orderservice.u_hexagonal.application.service;

import com.example.orderservice.domain.OrderEntity;
import com.example.orderservice.domain.OrderResponseDTO;
import com.example.orderservice.u_hexagonal.adapter.in.web.dto.OrderSelectAllResponseDTO;
import com.example.orderservice.u_hexagonal.application.port.in.OrderSelectAllInPort;
import com.example.orderservice.u_hexagonal.application.port.out.OrderSelectAllOutPort;
import com.example.orderservice.u_hexagonal.domain.OrderSelectAllDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderSelectAllService implements OrderSelectAllInPort {

    private final OrderSelectAllOutPort orderSelectAllOutPort;

    @Override
    public List<OrderSelectAllResponseDTO> findAllByCustomerId(Long customerId) {

        // out port를 호출한다.
        // domain 을 받는다.
        List<OrderSelectAllDomain> orderSelectAllDomainList = orderSelectAllOutPort.getOrderSelectAllDomain(customerId);

        // domain 을 OrderSelectAllResponseDTO로 변경한다.
        ModelMapper mapper = new ModelMapper();
        List<OrderSelectAllResponseDTO> OrderSelectAllResponseDtoList = orderSelectAllDomainList.stream()
                .map(data ->
                        mapper.map(data, OrderSelectAllResponseDTO.class))
                .collect(Collectors.toList());

        return OrderSelectAllResponseDtoList;

    }
}
