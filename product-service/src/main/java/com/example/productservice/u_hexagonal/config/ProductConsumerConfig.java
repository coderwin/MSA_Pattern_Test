package com.example.productservice.u_hexagonal.config;

import com.example.productservice.u_hexagonal.adapter.in.dto.KafkaOrderProductSaveRequestDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableKafka
public class ProductConsumerConfig {

    @Value("${kafka.server.ip}")
    private String ip;

    /**
     * 기능 : kafka consumer factory 속성
     */
    private Map<String, Object> productConsumerProperties() {

        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ip);

        return props;
    }

    /**
     * 기능 : kafak consumer factory 생성
     */
    private DefaultKafkaConsumerFactory<String, List<KafkaOrderProductSaveRequestDTO>> productDefaultKafkaConsumerFactory() {
        DefaultKafkaConsumerFactory<String, List<KafkaOrderProductSaveRequestDTO>> defaultKafkaConsumerFactory =
                new DefaultKafkaConsumerFactory<>(
                        productConsumerProperties(),
                        new StringDeserializer(),
                        new JsonDeserializer<List<KafkaOrderProductSaveRequestDTO>>()
                );
        return defaultKafkaConsumerFactory;
    }

    /**
     * 기능 : 비동기 Kafka consumer 생성을 위한 Kafka
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, List<KafkaOrderProductSaveRequestDTO>> productConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, List<KafkaOrderProductSaveRequestDTO>> containerFactory
                = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(productDefaultKafkaConsumerFactory());
//        containerFactory.setAutoStartup(true);// 사용자가 consumer 시작, 정지를 조절한다.

        return containerFactory;
    }


















}
