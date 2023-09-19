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

/**
 * 회원 Entity - 회원의 정보를 저장한다.
 */
@Entity
@Getter
@Setter
public class MemberEntity extends TimeStamp implements UserEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password; //비밀번호

    private String username; //로그인 아이디

    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private final Role role = Role.MEMBER;

    @Enumerated(EnumType.STRING)
    private MemberClass memberClass = MemberClass.BRONZE; //회원 등급


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupMemberEntity> groupMemberList = new ArrayList<>();

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private CartEntity cart; //회원마다 하나의 장바구니를 가진다

    @OneToMany(mappedBy = "member")
    private List<OrderEntity> orderList = new ArrayList<>();

    public void setMemberInGroupMemberList() {
        groupMemberList.forEach(o->o.setMember(this));
    }
}
