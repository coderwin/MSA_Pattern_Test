package com.example.orderservice.u_hexagonal.application.service;

import com.example.orderservice.u_hexagonal.adapter.in.web.dto.OrderSaveRequestDTO;
import com.example.orderservice.u_hexagonal.application.port.in.OrderSaveInPort;
import com.example.orderservice.u_hexagonal.application.port.out.OrderSaveOutPort;
import com.example.orderservice.u_hexagonal.domain.vo.OrderProductSaveDomain;
import com.example.orderservice.u_hexagonal.domain.vo.OrderSaveDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderSaveService implements OrderSaveInPort {

    private final OrderSaveOutPort orderSaveOutPort;

    @Override
    @Transactional
    public void save(OrderSaveRequestDTO orderRequest) {
        //  //주문한상품들에 대한 정보를 저장할 수 있도록 생성
        ModelMapper mapper = new ModelMapper();
        log.info("orderdetaillist=>{}",orderRequest);

        // OrderProduct domain vo 생성하기
        List<OrderProductSaveDomain> detaillist = orderRequest.getOrderProductSaveRequestDtoList().stream()
                .map((detailDTO) ->{
                    return mapper.map(detailDTO,OrderProductSaveDomain.class);
                }).collect(Collectors.toList());

        log.info("================================================");
        log.info("orderdetaillist=>{}",detaillist);
        log.info("================================================");


        // Order domain vo 생성하기
        OrderSaveDomain orderSaveDomain =
                OrderSaveDomain.makeOrderSaveDomain(orderRequest.getAddr(), orderRequest.getCustomerId(), detaillist);
        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        log.info("저장전:{}",orderSaveDomain);

        //양방향관계인 경우 부모테이블과 자식테이블의 데이터를 모두 영속화시켜주는 작업을 처리하면서
        //부모테이블에 레코드를 저장할때 자식테이블의 레코드를 한 번에 같이 진행할 수 있다.
        //테이블에 cascade = CascadeType.ALL이라는 옵션을 정의해 주어야 한다.

        // Order InPort save 실행한다.
//        dao.save(orderEntity);
        orderSaveOutPort.save(orderSaveDomain);
    }
}
