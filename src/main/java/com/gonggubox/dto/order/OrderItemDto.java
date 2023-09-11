package com.gonggubox.dto.order;

import com.gonggubox.dto.item.ItemDto;
import lombok.*;

public class OrderItemDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderItemPostDto {

        private Long itemId;

        private Long count;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderItemResponseDto {

        private ItemDto.ItemResponseDto itemResponseDto;

        private Long count;

    }

}
