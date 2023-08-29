package com.gonggubox.domain.cart;

import com.gonggubox.domain.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 장바구니 Entity - 회원마다 장바구니를 1개씩 가지고 있으며, 장바구니에 상품을 담아둘 수 있다.
 */
@Entity
@Getter
@Setter
public class CartEntity {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    private Long totalPrice; //가격이 변동될 수 있기 때문에 그떄그때 가져오는게 나을듯

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItemList = new ArrayList<>();

}
