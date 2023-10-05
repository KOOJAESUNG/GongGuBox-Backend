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
    public static class MemberJoinDto {

        //비밀번호 정규식. 8~15자 영문, 숫자, 특수문자 조합으로 이뤄져야한다.
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$",
                message = "비밀번호는 8~15자 영문, 숫자, 특수문자 조합이어야 합니다.")
        private String memberPassword;

        @NotBlank(message = "아이디를 작성해주세요.")
        private String memberUsername; //로그인 아이디

        @NotBlank(message = "이메일을 작성해주세요.")
        private String memberEmail;

        //핸드폰번호 정규식. '-'가 있어도 되고 없어도 된다.
        @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$",
                message = "휴대폰 번호를 정확하게 입력해주세요.")
        @NotBlank(message = "휴대폰 번호를 작성해주세요.")
        private String memberPhoneNumber;


    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberLoginDto { // todo : 어차피 admin도 동일한 dto를 사용할 것이므로 하나의 클래스로 따로 빼야함!!

        //비밀번호 정규식. 8~15자 영문, 숫자, 특수문자 조합으로 이뤄져야한다.
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$",
                message = "비밀번호는 8~15자 영문, 숫자, 특수문자 조합이어야 합니다.")
        private String password;

        @NotBlank(message = "아이디를 작성해주세요.")
        private String username; //로그인 아이디


    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberPostDto {

        @NotNull
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$",
                message = "비밀번호는 8~15자 영문, 숫자, 특수문자 조합이어야 합니다.")
        private String memberPassword;

        @NotBlank
        private String memberUsername; //로그인 아이디

        @NotBlank
        @Email
        private String memberEmail;

        @NotBlank
        @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$",
                message = "휴대폰 번호를 정확하게 입력해주세요.")
        private String memberPhoneNumber;

        @UniqueElements
        @NotNull
        private List<Long> groupIdList; //member가 속한 group의 id List

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberResponseDto {

        private String memberUsername; //로그인 아이디

        private String memberEmail;

        private String memberPhoneNumber;

        private MemberClass memberClass;

        private List<GroupDto.GroupResponseDto> groupInfoList;

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberOrderResponseDto {

        private List<OrderDto.OrderResponseDto> memberOrderList; //todo : orderservice or memberservice??
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberPatchDto {

        private String memberUsername; //로그인 아이디

        private String memberPassword;

        private String memberEmail;

        private String memberPhoneNumber;

        private MemberClass memberClass;

        private List<Long> groupIdList;

    }
}