package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    //== 생성 메서드 ==//
    public static OrderItem createOderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.reduceStockQuantity(count);
        return orderItem;
    }

    //== 비즈니스 로직 ==//
    /**
     * 주문 취소 재고 원복
     */
    public void cancel() {
         item.increaseStockQuantity(count);
    }

    //== 조회 로직 ==//

    /**
     * 주문 상품 전체 가격
     */
    public int getTotalPrice() {
        return orderPrice * count;
    }
}
