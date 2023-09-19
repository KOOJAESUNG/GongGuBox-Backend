package com.gonggubox.dto.member;

import com.gonggubox.constant.MemberClass;
import com.gonggubox.dto.order.OrderDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberPostDto {

        @NotNull
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$",
                message = "비밀번호는 8~15자 영문, 숫자, 특수문자 조합이어야 합니다.")
        private String password;

        @NotBlank
        private String username; //로그인 아이디

        @NotBlank
        @Email
        private String email;

        @NotBlank
        @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$",
                message = "휴대폰 번호를 정확하게 입력해주세요.")
        private String phoneNumber;

        @UniqueElements
        @NotNull
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