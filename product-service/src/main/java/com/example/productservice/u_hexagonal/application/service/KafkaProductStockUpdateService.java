package com.example.productservice.u_hexagonal.application.service;

import com.example.productservice.u_hexagonal.adapter.in.dto.KafkaOrderProductSaveRequestDTO;
import com.example.productservice.u_hexagonal.application.port.in.KafkaProductStockUpdateInPort;
import com.example.productservice.u_hexagonal.application.port.out.KafkaProductStockUpdateOutPort;
import com.example.productservice.u_hexagonal.domain.vo.KafkaOrderProductSaveDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
@Slf4j
public class KafkaProductStockUpdateService implements KafkaProductStockUpdateInPort {


    private final KafkaProductStockUpdateOutPort kafkaProductStockUpdateOutPort;

    /**
     * 궁금한 점:
     *          - service 로직 처리는 inPort에서 하나요, out Port에서 하나요?
     *          - db 데이터를 불러서 오는 경우는 out port에서 하나요?
     * @param request
     */
    @Transactional
    @Override
    public void subtractStock(List<KafkaOrderProductSaveRequestDTO> request) {

        ModelMapper mapper = new ModelMapper();

        // dto -> domain vo로 변경하기
        List<KafkaOrderProductSaveDomain> kafkaOrderProductSaveDomainList = request.stream()
                .map((data) -> {
                    return mapper.map(data, KafkaOrderProductSaveDomain.class);
                })
                .collect(toList());

        // out port로 전달하기
        kafkaProductStockUpdateOutPort.subtractStock(kafkaOrderProductSaveDomainList);



    }
}
