package com.gonggubox.domain.cart;

import com.gonggubox.domain.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private MemberEntity member;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CartItemEntity> cartItemList = new ArrayList<>();

}
