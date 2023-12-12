package com.example.orderservice.service;

import com.example.orderservice.domain.OrderDetailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderStringProducer {
    private final KafkaTemplate<String,String> kafkaTemplate;
    //물건이 주문될 때 메시지전송
    public void sendMessage(String topic, OrderDetailDTO orderInfo){
       //메시지전송   OrderDetailDTO => String
        ObjectMapper mapper = new ObjectMapper();
        String orderStr = "";
        try {
            orderStr = mapper.writeValueAsString(orderInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //publish
        kafkaTemplate.send(topic,orderStr);
        log.info("#############################################################");
        log.info("order-service의 프로듀서가 메시지전송:{} ",orderStr);
        log.info("#############################################################");
    }


}











