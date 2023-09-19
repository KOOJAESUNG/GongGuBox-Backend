package com.gonggubox.dto.order;

import com.gonggubox.domain.order.OrderItemEntity;
import lombok.*;

import java.util.List;

public class OrderDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderPostDto {

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
        private Long id;

        private List<OrderItemEntity> orderItems; // todo : OrderItemDto 로 변경해야함!!
    }
}
