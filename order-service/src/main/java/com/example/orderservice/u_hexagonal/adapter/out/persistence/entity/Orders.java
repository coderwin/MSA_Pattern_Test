package com.example.orderservice.u_hexagonal.adapter.out.persistence.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Slf4j
public class Orders {

    @Id
    @GeneratedValue
    private Long orderId;
    @CreationTimestamp
    private Date orderDate;//주문한시간
    private String addr; //배송주소
    //고객이 여러 건의 주문을 할 수 있으므로 - 다대일관계
    private Long customerId;
    //주문일반1에 대해서 구매한 정보(orderDetail)
    @OneToMany(mappedBy =  "orders",cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "orders")
    private List<OrderProduct> orderProductList = new ArrayList<>();

    public Orders(String addr, Long customerId) {
        this.addr = addr;
        this.customerId = customerId;
    }

    //양방향매핑으로 양쪽 엔티티에서 각각에 대한 정보를 가지고 있어야 하므로 반영
    public void addOrderProduct(OrderProduct orderProduct){
        orderProductList.add(orderProduct);
        orderProduct.addOrders(this);
    }

}
