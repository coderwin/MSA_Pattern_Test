package com.example.productservice.u_hexagonal.adapter.out.persistence;

import com.example.productservice.u_hexagonal.adapter.out.persistence.entity.Product;
import com.example.productservice.u_hexagonal.adapter.out.persistence.repoistory.HexagonalProductRepository;
import com.example.productservice.u_hexagonal.application.port.out.KafkaProductStockUpdateOutPort;
import com.example.productservice.u_hexagonal.domain.vo.KafkaOrderProductSaveDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
//@Component// ?????? 여기도 서비스 처리같아서 service가 맞는 거 같은데?????
@Slf4j
public class KafkaProductStockUpdateOutAdapter implements KafkaProductStockUpdateOutPort {

    private final HexagonalProductRepository productRepository;

    @Override
    public void subtractStock(List<KafkaOrderProductSaveDomain> kafkaOrderProductSaveDomainList) {

        // 루프를 돌며 db 데이터 불러오기
        kafkaOrderProductSaveDomainList.stream()
                // id에 따라 count 변경하기
                .forEach((item) -> {
                    // 아이디로 product 불러오기
                    Product product = productRepository.findById(item.getProductNo()).orElseThrow(() -> {
                        String errMsg = "존재하지 않는 상품입니다.";
                        return new IllegalArgumentException(errMsg);
                    });
                    // 재고량 변경하기
                    product.subtractStock(item.getCount());
                });
    }
}
