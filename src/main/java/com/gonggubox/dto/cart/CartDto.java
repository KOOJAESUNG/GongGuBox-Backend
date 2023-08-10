package com.gonggubox.dto.cart;


import com.gonggubox.domain.cart.CartItemEntity;
import com.gonggubox.domain.member.MemberEntity;
import lombok.*;

import java.util.List;

public class CartDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CartPostDto {

        private List<CartItemEntity> cartItemList;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CartResponseDto {
        private Long id;

        private MemberEntity member;

        private Long totalPrice;

        private List<CartItemEntity> cartItemList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CartPatchDto {

        private Long id;

        private List<CartItemEntity> cartItemList;
    }
}
