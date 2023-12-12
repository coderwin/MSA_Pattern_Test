package com.example.productservice.dao;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductDAOImpl implements ProductDAO{
    private final ProductRepository repository;
    @Override
    public void write(Product product) {

    }

    @Override
    public void insertAll(List<Product> prdlist) {
        repository.saveAll(prdlist);
    }

    @Override
    public Product findByProductNo(Long productNo) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(Map<Object, Object> product) {
        int key = (Integer) product.get("productNo");
        //조회하기
        Product entity = repository.findById(new Long(key)).get();
        log.info("조회=>{}",entity);
        if(entity!=null){
            //재고 수정
            entity.setStock(entity.getStock() - (Integer)product.get("count"));
            repository.save(entity);
        }
    }
}
