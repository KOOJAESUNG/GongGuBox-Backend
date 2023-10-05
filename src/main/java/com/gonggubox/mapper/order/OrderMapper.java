package com.gonggubox.mapper.order;

import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.domain.order.OrderEntity;
import com.gonggubox.domain.order.OrderItemEntity;
import com.gonggubox.dto.order.OrderDto;
import com.gonggubox.dto.order.OrderItemDto;
import com.gonggubox.mapper.item.ItemMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public abstract class OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "modifiedAt", ignore = true),
            @Mapping(target = "totalPrice", ignore = true), //todo : 구현
            @Mapping(source = "orderPostDto.orderItemList", target = "orderItemList", qualifiedByName = "orderItemPostDtoListToOrderItemEntityList"),
            @Mapping(source = "member", target = "member")
    })
    public abstract OrderEntity toEntity(OrderDto.OrderPostDto orderPostDto, MemberEntity member);

    @Named("orderItemPostDtoListToOrderItemEntityList")
    List<OrderItemEntity> orderItemPostDtoListToOrderItemEntityList(List<OrderItemDto.OrderItemPostDto> orderItemPostDtoList) {
        if (orderItemPostDtoList == null) return new ArrayList<>();
        List<OrderItemEntity> temp = new ArrayList<>();
        orderItemPostDtoList.forEach(o -> temp.add(orderItemMapper.toEntity(o)));
        return temp;
    }

    @Mappings({
            @Mapping(target = "orderedMemberId", expression = "java(orderEntity.getMember().getId())"),
            @Mapping(source = "orderItemList", target = "orderItemInfoList", qualifiedByName = "orderItemListToOrderItemResponseDtoList"),
            @Mapping(source = "id", target = "orderId"),
            @Mapping(source = "totalPrice", target = "orderTotalPrice"),
    })
    public abstract OrderDto.OrderResponseDto toResponseDto(OrderEntity orderEntity);

    @Named("orderItemListToOrderItemResponseDtoList")
    List<OrderItemDto.OrderItemResponseDto> orderItemListToOrderItemResponseDtoList(List<OrderItemEntity> orderItems) {
        if (orderItems == null) return new ArrayList<>();
        List<OrderItemDto.OrderItemResponseDto> temp = new ArrayList<>();
        orderItems.forEach(o -> temp.add(orderItemMapper.toResponseDto(o)));
        return temp;
    }

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mappings({
//            @Mapping(target = "id", ignore = true),
//            @Mapping(target = "createAt", ignore = true),
//            @Mapping(target = "modifiedAt", ignore = true),
//            @Mapping(target = "member", ignore = true),
//            @Mapping(target = "totalPrice", ignore = true)
//    })
//    public abstract void updateFromPatchDto(OrderDto.OrderPatchDto orderPatchDto, @MappingTarget OrderEntity orderEntity);

}
