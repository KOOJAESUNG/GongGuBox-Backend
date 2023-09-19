package com.gonggubox.domain.order;

import com.gonggubox.domain.item.ItemEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 주문과 상품의 다대다 관계를 풀기 위한 Entity. 주문 당시의 상품 가격과 개수를 포함한다.
 */
@Entity
@Getter
@Setter
public class OrderItemEntity {

    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @NotNull
    @Min(1)
    private Long orderPrice; //주문 당시의 상품 가격

    @NotNull
    @Min(1)
    private int count; //주문한 상품의 개수

}
