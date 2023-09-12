package com.gonggubox.domain.order;


import com.gonggubox.domain.TimeStamp;
import com.gonggubox.domain.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 주문정보 Entity - 회원의 각 주문에 대한 정보를 가진다
 */
@Entity
@Getter
@Setter
public class OrderEntity extends TimeStamp {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    private Long totalPrice; //주문의 총 금액

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems = new ArrayList<>();


    public void setOrderInOrderItems() {
        orderItems.forEach(o->o.setOrder(this));
    }

}
