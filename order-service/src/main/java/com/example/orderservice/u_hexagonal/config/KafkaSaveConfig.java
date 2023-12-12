package com.example.orderservice.u_hexagonal.config;

import com.example.orderservice.u_hexagonal.adapter.out.kafka.dto.KafkaOrderProductSaveRequestDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 설명 : Producer 생성하기
 */
@Configuration
@EnableKafka
public class KafkaSaveConfig {

    @Value("${kafka.server.ip}")
    private String ip;
    @Value("${kafka.topic.order_service}")
    private String orderService;


    /**
     * 기능 : Kafka Properties
     */
    private Map<String, Object> properties() {

        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ip);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;

    }

    /**
     * 기능 : Kafak Factory
     */
    private ProducerFactory<String, List<KafkaOrderProductSaveRequestDTO>> producerOrderProductSaveFactory() {

        DefaultKafkaProducerFactory producerFactory = new DefaultKafkaProducerFactory<>(properties());

        return producerFactory;
    }

    /**
     * 기능 : Kafka Template
     */
    @Bean
    public KafkaTemplate<String, List<KafkaOrderProductSaveRequestDTO>> producerOrderProductSaveKafkaTemplate() {

        KafkaTemplate<String, List<KafkaOrderProductSaveRequestDTO>> kafkaTemplate  = new KafkaTemplate<>(producerOrderProductSaveFactory());
        return kafkaTemplate;
    }








}
