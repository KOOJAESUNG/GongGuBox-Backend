package com.gonggubox.mapper.order;

import com.gonggubox.domain.item.ItemEntity;
import com.gonggubox.domain.order.OrderItemEntity;
import com.gonggubox.dto.item.ItemDto;
import com.gonggubox.dto.order.OrderItemDto;
import com.gonggubox.mapper.item.ItemMapper;
import com.gonggubox.repository.item.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public abstract class OrderItemMapper {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "itemId",target = "item", qualifiedByName = "itemIdToItemEntity"),
            @Mapping(source = "itemId", target = "orderPrice", qualifiedByName = "itemIdToPrice"),
            @Mapping(target = "order", ignore = true)
    })
    public abstract OrderItemEntity toEntity(OrderItemDto.OrderItemPostDto orderItemPostDto);
    @Named("itemIdToPrice")
    Long itemIdToPrice(Long itemId) {
        return itemRepository.getPriceById(itemId).orElseThrow(EntityNotFoundException::new);
    }
    @Named("itemIdToItemEntity")
    ItemEntity itemIdToItemEntity(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
    }


    @Mappings({
            @Mapping(source = "item", target = "itemResponseDto", qualifiedByName = "itemEntityToItemResponseDto")
    })
    public abstract OrderItemDto.OrderItemResponseDto toResponseDto(OrderItemEntity orderItem);
    @Named("itemEntityToItemResponseDto")
    ItemDto.ItemResponseDto itemEntityToItemResponseDto(ItemEntity item) {
        return itemMapper.toResponseDto(item);
    }
//
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mappings({
//            @Mapping(target = "id", ignore = true),
//            @Mapping(target = "createAt", ignore = true),
//            @Mapping(target = "modifiedAt", ignore = true),
//            @Mapping(target = "member", ignore = true),
//            @Mapping(target = "totalPrice", ignore = true)
//    })
//    public abstract void updateFromPatchDto(OrderDto.OrderPatchDto OrderPatchDto, @MappingTarget OrderEntity OrderEntity);
}
