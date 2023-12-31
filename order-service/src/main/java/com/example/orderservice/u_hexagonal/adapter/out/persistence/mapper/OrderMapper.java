package com.example.orderservice.u_hexagonal.adapter.out.persistence.mapper;

import com.example.orderservice.u_hexagonal.adapter.out.kafka.dto.KafkaOrderProductSaveRequestDTO;
import com.example.orderservice.u_hexagonal.adapter.out.persistence.entity.OrderProduct;
import com.example.orderservice.u_hexagonal.adapter.out.persistence.entity.Orders;
import com.example.orderservice.u_hexagonal.domain.OrderProductSelectAllDomain;
import com.example.orderservice.u_hexagonal.domain.OrderSelectAllDomain;
import com.example.orderservice.u_hexagonal.domain.vo.OrderProductSaveDomain;
import com.example.orderservice.u_hexagonal.domain.vo.OrderSaveDomain;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 설명 : domain을 entity로 또는
 *       entity를 domain으로 변경
 */
@Component
public class OrderMapper {


    public OrderProduct orderProductSaveDomainToOrderProduct(OrderProductSaveDomain orderProductSaveDomain) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(orderProductSaveDomain, OrderProduct.class);
    }


    public Orders orderSaveDomainToOrders(OrderSaveDomain orderSaveDomain) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(orderSaveDomain, Orders.class);
    }

    public OrderSelectAllDomain ordersToOrderSelectAllDomain(Orders orders) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(orders, OrderSelectAllDomain.class);

    }

    public OrderProductSelectAllDomain orderProductToOrderProductSelectAllDomain(OrderProduct orderProduct) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(orderProduct, OrderProductSelectAllDomain.class);
    }

    public KafkaOrderProductSaveRequestDTO orderProductSaveDomainToKafkaOrderProductSaveRequestDTO(OrderProductSaveDomain orderProductSaveDomain) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(orderProductSaveDomain, KafkaOrderProductSaveRequestDTO.class);

    }
}
