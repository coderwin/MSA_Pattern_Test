package com.example.orderservice.u_hexagonal.adapter.out.persistence.entity;

import com.example.orderservice.domain.OrderEntity;
import com.example.orderservice.domain.OrderProductEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @OneToMany(mappedBy =  "myorder",cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "myorder")
    private List<OrderProduct> orderproductlist = new ArrayList<>();

    public Orders(String addr, Long customerId) {
        this.addr = addr;
        this.customerId = customerId;
    }

    //양방향매핑으로 양쪽 엔티티에서 각각에 대한 정보를 가지고 있어야 하므로 반영
    public void addOrderProduct(OrderProduct orderProduct){
        orderproductlist.add(orderProduct);
        orderProduct.addOrders(this);
    }
    public static OrderEntity makeOrderEntity(String addr, Long customerId,
                                              List<OrderProductEntity> list){
        OrderEntity entity = new OrderEntity(addr,customerId);
        for(OrderProductEntity orderdetail:list){
            entity.changeOrderDetailInfo(orderdetail);
        }
        log.info("*****************************************") ;
        log.info("값:{}",entity.getOrderproductlist());
        return entity;//만들어진 OrderEntity를 리턴
    }

}
