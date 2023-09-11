package com.gonggubox.dto.member;

import com.gonggubox.constant.MemberClass;
import com.gonggubox.domain.cart.CartItemEntity;
import com.gonggubox.domain.member.GroupMemberEntity;
import com.gonggubox.domain.order.OrderEntity;
import lombok.*;

import java.util.List;

public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberPostDto {

        private String password;

        private String username; //로그인 아이디

        private String email;

        private String phoneNumber;

        private List<GroupMemberEntity> groupMemberList;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberResponseDto {

        private String username; //로그인 아이디

        private String email;

        private String phoneNumber;

        private MemberClass memberClass;

        private List<GroupMemberEntity> groupMemberList; //todo : 변경 요망

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberCartResponseDto {

        private List<CartItemEntity> cartItemList;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberOrderResponseDto {

        private List<OrderEntity> orderList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberPatchDto {

        private String username; //로그인 아이디

        private String password;

        private String email;

        private String phoneNumber;


        private MemberClass memberClass;

        private List<GroupMemberEntity> groupMemberList;

    }
}