package com.gonggubox.mapper.member;

import com.gonggubox.domain.member.GroupEntity;
import com.gonggubox.dto.member.GroupDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface GroupMapper {

    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "itemList",ignore = true)
    })
    GroupEntity toEntity(GroupDto.GroupPostDto GroupPostDto);



    GroupDto.GroupResponseDto toResponseDto(GroupEntity GroupEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "itemList", ignore = true),
    })
    public void updateFromPatchDto(GroupDto.GroupPatchDto GroupPatchDto, @MappingTarget GroupEntity GroupEntity);

}
