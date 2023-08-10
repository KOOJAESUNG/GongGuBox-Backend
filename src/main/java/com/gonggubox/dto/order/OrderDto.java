package com.gonggubox.dto.order;

import com.gonggubox.domain.member.MemberEntity;
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

        private List<OrderItemEntity> orderItems;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderResponseDto {
        private Long id;

        private MemberEntity member;

        private Long totalPrice;

        private List<OrderItemEntity> orderItems;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderPatchDto {
        private Long id;

        private List<OrderItemEntity> orderItems;
    }
}
