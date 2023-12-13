package com.example.productservice.u_hexagonal.adapter.in.kafka;

import com.example.productservice.u_hexagonal.adapter.in.dto.KafkaOrderProductSaveRequestDTO;
import com.example.productservice.u_hexagonal.application.port.in.KafkaProductStockUpdateInPort;
import com.example.productservice.u_hexagonal.common.InAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 설명 : 주문 저장 후 넘어오는 OrderProduct List 데이터를 받는다.
 */
@RequiredArgsConstructor
@Component// ????? 이 위치는 어떤 @를 써야할까요? 스프링 컨테이너 등록 위해????
@InAdapter
@Slf4j
public class KafkaProductStockUpdateInAdapter {


    private final KafkaProductStockUpdateInPort kafkaProductStockUpdateInPort;

    /**
     * 기능 : order-service의 producer로부터 orderProduct(주문상품 목록 ) 데이터를 받기
     * @param request
     */
    @KafkaListener(
            id = "order_topic_id_1",
            groupId = "order_topic_gorup_id_1",
            topics = {"order-topic"},
            containerFactory = "productConcurrentKafkaListenerContainerFactory"
    )
    public void subtractStock(List<KafkaOrderProductSaveRequestDTO> request) {
        log.info("request : ", request);// ?????? request에는 안 찍히네요???????

        // in port로 전달하기
        kafkaProductStockUpdateInPort.subtractStock(request);


    }


}
