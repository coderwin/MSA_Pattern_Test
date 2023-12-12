package com.example.orderservice.u_hexagonal.adapter.out.persistence.entity;

import com.example.orderservice.domain.OrderEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
public class OrderProduct {

    @Id
    @GeneratedValue
    private Long orderDetailId;
    private Long productNo;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private Orders orders;
    private int orderPrice;//구매가격
    private int count;//주문수량

    public OrderProduct(Long productNo, Orders orders, int orderPrice, int count) {
        this.productNo = productNo;
        this.orders = orders;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public OrderProduct(Long productNo, int orderPrice, int count) {
        this.productNo = productNo;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    // setter 메서드
    public void addOrders(Orders orders) {

        this.orders = orders;
    }
}









