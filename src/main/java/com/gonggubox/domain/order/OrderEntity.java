package com.gonggubox.domain.order;


import com.gonggubox.domain.TimeStamp;
import com.gonggubox.domain.member.MemberEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @NotNull
    @Min(1)
    private Long totalPrice; //주문의 총 금액

    @UniqueElements
    @NotNull
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItemList = new ArrayList<>();


    public void setOrderInOrderItems() {
        orderItemList.forEach(o->o.setOrder(this));
    }

}
