package com.gonggubox.dto.admin;

import lombok.*;

public class AdminDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AdminPostDto {

        private String password;

        private String username; //로그인 아이디

        private String email;

        private String phoneNumber;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AdminResponseDto {
        private Long id;

        private String username; //로그인 아이디

        private String email;

        private String phoneNumber;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AdminPatchDto {

        private String password;

        private String username; //로그인 아이디

        private String email;

        private String phoneNumber;
    }
}
