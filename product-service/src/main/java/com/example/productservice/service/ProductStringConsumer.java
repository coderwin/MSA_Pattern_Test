package com.example.productservice.service;

import com.example.productservice.dao.ProductDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductStringConsumer {
    //브로커로부터 메시지를 받으면 db에 데이터를 수정(재고수정)
    private final ProductDAO dao;
    @KafkaListener(topics = "ordercreate",groupId = "updatestockgroup",
        containerFactory = "getKafkalistenerconatainer")
    public void receiveMsg(String message){

        Map<Object,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(message,
                    new TypeReference<Map<Object, Object>>() {});
            log.info("전달받은 메시지=====>{}",message);
            log.info("전달받은 메시지를 변환=====>{}",map);
            //메시지받은 후에 상품재고 수정
            dao.update(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
