package com.example.orderservice.u_hexagonal.adapter.out.kafka;

import com.example.orderservice.u_hexagonal.adapter.out.kafka.dto.KafkaOrderProductSaveRequestDTO;
import com.example.orderservice.u_hexagonal.adapter.out.persistence.mapper.OrderMapper;
import com.example.orderservice.u_hexagonal.application.port.out.KafkaEventOrderSaveOutPort;
import com.example.orderservice.u_hexagonal.common.OutAdapter;
import com.example.orderservice.u_hexagonal.domain.vo.OrderProductSaveDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
@OutAdapter
@Slf4j
public class KafkaEventOrderSaveOutAdapter implements KafkaEventOrderSaveOutPort {

    @Value("${kafka.topic.order_service}")
    private String orderTopic;

    private final OrderMapper orderMapper;
    private final KafkaTemplate<String, List<KafkaOrderProductSaveRequestDTO>> kafkaTemplate;

    @Override
    public void orderEvent(List<OrderProductSaveDomain> orderProductSaveDomainList) {

        // domain을 responseDto로 변경한다.
        List<KafkaOrderProductSaveRequestDTO> request = orderProductSaveDomainList.stream()
                .map(orderMapper::orderProductSaveDomainToKafkaOrderProductSaveRequestDTO)
                .collect(toList());

        // order_topic으로 메시지를 전송한다.
        kafkaTemplate.send(orderTopic, request);


    }






}
