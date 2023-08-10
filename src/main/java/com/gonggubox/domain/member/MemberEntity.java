package com.gonggubox.domain.member;


import com.gonggubox.constant.MemberClass;
import com.gonggubox.constant.Role;
import com.gonggubox.domain.TimeStamp;
import com.gonggubox.domain.UserEntity;
import com.gonggubox.domain.cart.CartEntity;
import com.gonggubox.domain.order.OrderEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class MemberEntity extends TimeStamp implements UserEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;

    private String username; //로그인 아이디

    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private final Role role = Role.MEMBER;

    @Enumerated(EnumType.STRING)
    private MemberClass memberClass = MemberClass.BRONZE;


    @OneToMany(mappedBy = "member")
    private List<GroupMemberEntity> groupMemberList = new ArrayList<>();

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private CartEntity cart;

    @OneToMany(mappedBy = "member")
    private List<OrderEntity> orderList = new ArrayList<>();
}
