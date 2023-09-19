package com.gonggubox.dto.member;

import com.gonggubox.constant.MemberClass;
import com.gonggubox.dto.order.OrderDto;
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

        private List<Long> groupIdList;

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

        private List<GroupDto.GroupResponseDto> groupList;

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberOrderResponseDto {

        private List<OrderDto.OrderResponseDto> orderList; //todo : orderservice or memberservice??
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

        private List<Long> groupIdList;

    }
}