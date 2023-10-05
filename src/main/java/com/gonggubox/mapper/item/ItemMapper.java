package com.gonggubox.mapper.item;

import com.gonggubox.domain.item.ItemEntity;
import com.gonggubox.domain.member.GroupEntity;
import com.gonggubox.dto.item.ItemDto;
import com.gonggubox.dto.member.GroupDto;
import com.gonggubox.repository.member.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public abstract class ItemMapper {

    @Autowired
    private GroupRepository groupRepository;

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "modifiedAt", ignore = true),
            @Mapping(target = "imageList", ignore = true),
            @Mapping(target = "itemStatus", ignore = true),
            @Mapping(target = "orderItemList", ignore = true),
            @Mapping(source = "itemName", target = "name"),
            @Mapping(source = "itemPrice", target = "price"),
            @Mapping(source = "totalItemCount", target = "count"),
            @Mapping(source = "meetingAddress", target = "address"),
            @Mapping(source = "itemExplain", target = "content"),
            @Mapping(source = "groupId", target = "group", qualifiedByName = "groupIdToGroup")
    })
    public abstract ItemEntity toEntity(ItemDto.ItemPostDto ItemPostDto);

    @Named("groupIdToGroup")
    GroupEntity groupIdToGroup(Long groupId) {
        if (groupId == null) return null;
        return groupRepository.findById(groupId).orElseThrow(EntityNotFoundException::new);
    }


    @Mappings({
            @Mapping(source = "id", target = "itemId"),
            @Mapping(source = "name", target = "itemName"),
            @Mapping(source = "imageList", target = "itemImageList"),
            @Mapping(source = "price", target = "itemPrice"),
            @Mapping(source = "content", target = "itemExplain"),
            @Mapping(source = "count", target = "totalItemCount"),
            @Mapping(source = "address", target = "meetingAddress"),
            @Mapping(source = "group", target = "groupInfo", qualifiedByName = "groupToGroupInfo"),
    })
    public abstract ItemDto.ItemResponseDto toResponseDto(ItemEntity itemEntity);

    @Named("groupToGroupInfo")
    GroupDto.GroupResponseDto groupToGroupInfo(GroupEntity group) {
        if(group == null) return null;
        return GroupDto.GroupResponseDto.builder()
                .groupId(group.getId())
                .groupAddress(group.getAddress())
                .groupName(group.getName())
                .build();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "modifiedAt", ignore = true),
            @Mapping(target = "orderItemList", ignore = true),
            @Mapping(target = "imageList", ignore = true),
            @Mapping(target = "group", ignore = true),
            @Mapping(source = "itemName", target = "name"),
            @Mapping(source = "itemPrice", target = "price"),
            @Mapping(source = "totalItemCount", target = "count"),
            @Mapping(source = "meetingAddress", target = "address"),
            @Mapping(source = "itemExplain", target = "content"),
    })
    public abstract void updateFromPatchDto(ItemDto.ItemPatchDto ItemPatchDto, @MappingTarget ItemEntity ItemEntity);

}
