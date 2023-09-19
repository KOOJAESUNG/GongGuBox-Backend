package com.gonggubox.mapper.member;

import com.gonggubox.domain.item.ItemEntity;
import com.gonggubox.domain.member.GroupEntity;
import com.gonggubox.dto.item.ItemDto;
import com.gonggubox.dto.member.GroupDto;
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
public abstract class GroupMapper {

    @Autowired
    private ItemMapper itemMapper;

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "itemList", ignore = true)
    })
    public abstract GroupEntity toEntity(GroupDto.GroupPostDto GroupPostDto);


    public abstract GroupDto.GroupResponseDto toResponseDto(GroupEntity GroupEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "itemList", ignore = true),
    })
    public abstract void updateFromPatchDto(GroupDto.GroupPatchDto GroupPatchDto, @MappingTarget GroupEntity GroupEntity);


    @Mappings({
            @Mapping(source = "id", target = "groupId"),
            @Mapping(source = "name", target = "groupName"),
            @Mapping(source = "itemList", target = "itemList", qualifiedByName = "itemListToItemResponseDtoList")
    })
    public abstract GroupDto.GroupItemResponseDto toGroupItemResponseDto(GroupEntity group);

    @Named("itemListToItemResponseDtoList")
    List<ItemDto.ItemResponseDto> itemListToItemResponseDtoList(List<ItemEntity> itemEntityList) {
        if (itemEntityList == null) return new ArrayList<>();
        List<ItemDto.ItemResponseDto> temp = new ArrayList<>();
        itemEntityList.forEach(o -> temp.add(itemMapper.toResponseDto(o)));
        return temp;
    }

    ;

}
