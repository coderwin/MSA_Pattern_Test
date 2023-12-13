package com.example.productservice.u_hexagonal.adapter.out.persistence.entity;

import com.example.productservice.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
@Table(name = "product")
@DynamicInsert
public class Product {
    @Id
    @GeneratedValue
    private Long productNo;
    private String productName;
    private String info;
    private int price;
    private String image;
    @ColumnDefault("20")
    private Integer stock;//재고량
    private LocalDateTime insertTime;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String productName, String info, int price, String image, Category category) {
        this.productName = productName;
        this.info = info;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    // ***** 비즈니스 로직 *******
    /**
     * 기능 : 재고량을 뺄 수 있습니다.
     * @param count
     */
    public void subtractStock(int count) {
        if(Objects.nonNull(stock) || this.stock > 0) {
            this.stock -= count;
            return;
        }
        // 그 외에는 에러가 발생한다.
        String errMsg = "'" + this.productName + "' 상품 재고량이 부족합니다.";
        throw new IllegalArgumentException(errMsg);
    }
}
