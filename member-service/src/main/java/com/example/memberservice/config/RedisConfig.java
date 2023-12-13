package com.example.memberservice.config;

import com.example.memberservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private int port;
    @Value("${spring.data.redis.password}")
    private String password;
    @Bean
    public RedisTemplate<String, UserDto> redisTemplate(){
        RedisTemplate<String, UserDto> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory2());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //value가 object라는 것은 json으로 변환될 부분이라는 의미
        //json ->dto
        //dto -> json으로 자동변환
        redisTemplate.setValueSerializer(
                new Jackson2JsonRedisSerializer<UserDto>(UserDto.class));
        return  redisTemplate;
    }
    @Bean
    public RedisConnectionFactory redisConnectionFactory2(){
        RedisStandaloneConfiguration config =
                new RedisStandaloneConfiguration(host,port);
        config.setPassword(password);
        return new LettuceConnectionFactory(config);
    }

}
