package com.example.orderservice.domain;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
public class Article {

    @Id
    Long id;
    String title;
    String content;

}
