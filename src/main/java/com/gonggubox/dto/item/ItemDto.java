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
        private String itemName; //상품명

        @NotNull
        @Min(1)
        private Long itemPrice; //가격

        @NotNull
        @Min(1)
        private Integer totalItemCount; //수량. if 1+2 --> 3

        private String itemLink; //상품 링크

        private Address meetingAddress; // 약속장소

        private String itemExplain; //설명

        @NotNull
        @Min(1)
        private Long groupId; //그룹은 동명인 그룹이 여러개 존재할 수 있으므로 id 로 처리한다

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ItemResponseDto {
        private Long itemId;

        private String itemName; //상품명

        private List<String> itemImageList; //상품 이미지

        private Long itemPrice; //가격

        private Integer totalItemCount; //수량. if 1+2 --> 3

        private String itemLink; //상품 링크

        private Address meetingAddress; // 약속장소

        private String itemExplain; //설명

        private ItemStatus itemStatus; //상태. 모집중, 모집완료 등등

        private GroupDto.GroupResponseDto groupInfo;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ItemPatchDto {

        @NotNull
        @Min(1)
        private Long itemId;

        private String itemName; //상품명

//        private List<String> imageList; //상품 이미지

        private Long itemPrice; //가격

        private Integer totalItemCount; //수량. if 1+2 --> 3

        private String itemLink; //상품 링크

        private Address meetingAddress; // 약속장소

        private String itemExplain; //설명

        private ItemStatus itemStatus; //상태. 모집중, 모집완료 등등

        private Long groupId; //그룹은 동명인 그룹이 여러개 존재할 수 있으므로 id 로 처리한다

    }
}