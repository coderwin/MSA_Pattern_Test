package com.example.orderservice.u_hexagonal.adapter.in.web;

import com.example.orderservice.u_hexagonal.adapter.in.web.dto.OrderProductSaveRequestDTO;
import com.example.orderservice.u_hexagonal.adapter.in.web.dto.OrderSaveRequestDTO;
import com.example.orderservice.u_hexagonal.application.port.in.OrderSaveInPort;
import com.example.orderservice.u_hexagonal.common.InAdatpter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/orders")
@RequiredArgsConstructor
@RestController
@Slf4j
@InAdatpter
public class OrderSaveInAdapter {

    private final OrderSaveInPort orderServiceInPort;

    /**
     * 기능: 주문 저장
     * @param request
     */
    @PostMapping
    public void create(@RequestBody OrderSaveRequestDTO request){

        log.info("주문내역==>{}",request);

        orderServiceInPort.save(request);//주문하기
        //주문을 한 후 주문에 대한 결과를 리턴 받아서 주문이 성공된 것을 확인하고
        //주문된 상품 정보를 리턴받아서 이 정보로 메시지를 보낸다.
        //리턴받은 주문내역

//        /// kafka로 product로 전달한다.
//        List<OrderProductSaveRequestDTO> orderProductlist = request.getOrderDetailDTOList();
//
//        for(OrderProductSaveRequestDTO orderProduct:orderProductlist){
//            log.info("주문성공한 상품:{}",orderProduct);
//            producer.sendMessage("ordercreate",orderProduct);
//        }
    }















}
