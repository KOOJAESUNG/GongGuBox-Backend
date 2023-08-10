package com.gonggubox.mapper.member;

import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.dto.member.MemberDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface MemberMapper {

    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "createAt",ignore = true),
            @Mapping(target = "modifiedAt",ignore = true),
            @Mapping(target = "memberClass",ignore = true),
            @Mapping(target = "cart",ignore = true),
            @Mapping(target = "orderList",ignore = true),
    })
    MemberEntity toEntity(MemberDto.MemberPostDto MemberPostDto);



    MemberDto.MemberResponseDto toResponseDto(MemberEntity MemberEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt",ignore = true),
            @Mapping(target = "modifiedAt",ignore = true),
            @Mapping(target = "cart",ignore = true),
            @Mapping(target = "orderList",ignore = true)
    })
    public void updateFromPatchDto(MemberDto.MemberPatchDto MemberPatchDto, @MappingTarget MemberEntity MemberEntity);

}
