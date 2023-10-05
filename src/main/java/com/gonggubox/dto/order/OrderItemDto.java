package com.gonggubox.dto.order;

import com.gonggubox.dto.item.ItemDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class OrderItemDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderItemPostDto {

        @NotNull
        private Long itemId;

        @NotNull
        @Min(1)
        private Long count;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
//    @Builder
    public static class OrderItemResponseDto {

        private ItemDto.ItemResponseDto itemInfo;

        private Long count;

        private Long orderPrice;

    }

}
