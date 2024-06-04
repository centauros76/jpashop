package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //비지니스 로직//
    /**
     * 재고 증가
     */
    private void increaseStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * 재고 감소
     */
    private void reduceStockQuantity(int quantity) {
        int restStockQuantity = this.stockQuantity - quantity;
        if (restStockQuantity < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStockQuantity;
    }
}
