package com.example.orderservice.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

//사용자정의 시퀀스적용
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
        name = "my_generator",
        sequenceName = "test_sq",
        allocationSize = 1

)
public class test2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "my_generator")
    private Long id;
    private String info;

    public test2(String info) {
        this.info = info;
    }
}
