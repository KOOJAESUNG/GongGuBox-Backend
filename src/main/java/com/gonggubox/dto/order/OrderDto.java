package com.gonggubox.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public class OrderDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderPostDto {

        @NotNull
        @UniqueElements
        private List<OrderItemDto.OrderItemPostDto> orderItemList;

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderResponseDto {
        private Long orderId;

        private Long orderedMemberId;

        private Long orderTotalPrice;

        private List<OrderItemDto.OrderItemResponseDto> orderItemInfoList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderPatchDto {

        @NotNull
        @Min(1)
        private Long orderId;

        private List<OrderItemDto.OrderItemPostDto> orderItemList;
    }
}
