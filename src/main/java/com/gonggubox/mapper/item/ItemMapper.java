package com.gonggubox.mapper.item;

import com.gonggubox.domain.item.ItemEntity;
import com.gonggubox.dto.item.ItemDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface ItemMapper {

    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "createAt",ignore = true),
            @Mapping(target = "modifiedAt",ignore = true),
            @Mapping(target = "imageList",ignore = true),
            @Mapping(target = "itemStatus",ignore = true),
            @Mapping(target = "orderItem",ignore = true),
    })
    ItemEntity toEntity(ItemDto.ItemPostDto ItemPostDto);



    ItemDto.ItemResponseDto toResponseDto(ItemEntity ItemEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt",ignore = true),
            @Mapping(target = "modifiedAt",ignore = true),
            @Mapping(target = "orderItem",ignore = true)
    })
    public void updateFromPatchDto(ItemDto.ItemPatchDto ItemPatchDto, @MappingTarget ItemEntity ItemEntity);

}
