package com.gonggubox.domain.cart;

import com.gonggubox.domain.item.ItemEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 장바구니에 들어있는 상품이 몇개 들어있는지 저장하기 위한 Entity
 */
@Entity
@Getter
@Setter
public class CartItemEntity {

    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer count; //상품의 개수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    public static CartItemEntity createCartItemEntity(CartEntity cart, ItemEntity item, Integer count) {
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }
}
