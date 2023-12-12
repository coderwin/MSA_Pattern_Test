package com.example.orderservice.domain;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
public class Article {
    String title;
    String content;

}
