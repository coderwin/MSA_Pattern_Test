package com.example.orderservice.u_hexagonal.adapter.in.web;

import com.example.orderservice.domain.OrderResponseDTO;
import com.example.orderservice.u_hexagonal.adapter.in.web.dto.OrderSelectAllResponseDTO;
import com.example.orderservice.u_hexagonal.application.port.in.OrderSelectAllInPort;
import com.example.orderservice.u_hexagonal.common.InAdatpter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/orders")
@RequiredArgsConstructor
@RestController
@InAdatpter
@Slf4j
public class OrderSelectAllInAdapter {

    private final OrderSelectAllInPort orderSelectAllInPort;

    /**
     * 기능 : 주문 전체 불러오기 by customer id
     */
    @GetMapping("{customerId}")
    public List<OrderSelectAllResponseDTO> getOrders(@PathVariable Long customerId){

        List<OrderSelectAllResponseDTO> responselist =
                orderSelectAllInPort.findAllByCustomerId(customerId);
        log.info("컨트롤러:주문내역==>{}",responselist);
        return  responselist;
    }





}
