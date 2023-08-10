package com.gonggubox.mapper.item;

import com.gonggubox.domain.item.CategoryEntity;
import com.gonggubox.dto.item.CategoryDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface CategoryMapper {

    @Mappings({
            @Mapping(target = "id",ignore = true),
    })
    CategoryEntity toEntity(CategoryDto.CategoryPostDto CategoryPostDto);



    CategoryDto.CategoryResponseDto toResponseDto(CategoryEntity CategoryEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    public void updateFromPatchDto(CategoryDto.CategoryPatchDto CategoryPatchDto, @MappingTarget CategoryEntity CategoryEntity);

}