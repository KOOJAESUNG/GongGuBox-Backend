package com.gonggubox.dto.item;


import com.gonggubox.constant.Address;
import com.gonggubox.constant.ItemStatus;
import com.gonggubox.dto.member.GroupDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

public class ItemDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ItemPostDto {

        @NotBlank
        @Size(max = 50)
        private String name; //상품명

        @NotNull
        @Min(1)
        private Long price; //가격

        @NotNull
        @Min(1)
        private Integer count; //수량. if 1+2 --> 3

        private String itemLink; //상품 링크

        private Address address; // 약속장소

        private String content; //설명

        @NotNull
        @Min(1)
        private Long groupId;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ItemResponseDto {
        private Long id;

        private String name; //상품명

        private List<String> imageList; //상품 이미지

        private Long price; //가격

        private Integer count; //수량. if 1+2 --> 3

        private String itemLink; //상품 링크

        private Address address; // 약속장소

        private String content; //설명

        private ItemStatus itemStatus; //상태

        private GroupDto.GroupResponseDto group;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ItemPatchDto {

        @NotNull
        @Min(1)
        private Long id;

        private String name; //상품명

//        private List<String> imageList; //상품 이미지

        private Long price; //가격

        private Integer count; //수량. if 1+2 --> 3

        private String itemLink; //상품 링크

        private Address address; // 약속장소

        private String content; //설명

        private ItemStatus itemStatus; //상태

        private Long groupId;

    }
}