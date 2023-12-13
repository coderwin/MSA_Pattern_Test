package com.example.productservice.u_hexagonal.adapter.out.persistence.repoistory;

import com.example.productservice.u_hexagonal.adapter.out.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HexagonalProductRepository extends JpaRepository<Product, Long> {
}
