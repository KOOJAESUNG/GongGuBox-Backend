package com.gonggubox.dto.cart;


import com.gonggubox.dto.item.ItemDto;
import com.gonggubox.dto.member.MemberDto;
import lombok.*;

import java.util.List;

public class CartDto {

    /**
     * cart는 없으면 서버에서 만들어주면 되기 때문에 PostDto가 필요 없음!!
     */
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class CartPostDto {
//
//        private List<CartItemEntity> cartItemList;
//
//    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CartResponseDto {
        private Long id;

        private MemberDto.MemberResponseDto member;

        private Long totalPrice;

        private List<CartItemResponseDto> cartItemList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CartItemResponseDto {

        private Integer count; //상품의 개수

        private ItemDto.ItemResponseDto item;

    }

//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class CartPatchDto {
//
//        private Long id;
//
//        private List<CartItemEntity> cartItemList;
//    }
}
