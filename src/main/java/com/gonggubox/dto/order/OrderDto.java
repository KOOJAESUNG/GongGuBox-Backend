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
        private List<OrderItemDto.OrderItemPostDto> orderItemPostDtoList;

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderResponseDto {
        private Long id;

        private Long memberId;

        private Long totalPrice;

        private List<OrderItemDto.OrderItemResponseDto> orderItemResponseDtoList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderPatchDto {

        @NotNull
        @Min(1)
        private Long id;

        private List<OrderItemDto.OrderItemPostDto> orderItems;
    }
}
