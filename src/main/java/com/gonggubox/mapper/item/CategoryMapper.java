package com.gonggubox.mapper.item;

import com.gonggubox.domain.item.CategoryEntity;
import com.gonggubox.dto.item.CategoryDto;
import com.gonggubox.repository.item.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public abstract class CategoryMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "child", ignore = true),
            @Mapping(source = "parentCategoryName", target = "parent", qualifiedByName = "categoryNameToCategoryEntity")
    })
    public abstract CategoryEntity toEntity(CategoryDto.CategoryPostDto categoryPostDto);

    @Named("categoryNameToCategoryEntity")
    CategoryEntity categoryNameToCategoryEntity(String categoryName) {
        return categoryRepository.findByName(categoryName).orElse(null);
    }

    @Named("categoryNameListToCategoryEntityList")
    List<CategoryEntity> categoryNameListToCategoryEntityList(List<String> categoryNameList) {
        List<CategoryEntity> temp = new ArrayList<>();
        categoryNameList.forEach(o->temp.add(categoryNameToCategoryEntity(o)));
        return temp;
    }

    @Mappings({
            @Mapping(target = "parent", expression = "java(toIdNameDto(categoryEntity.getParent()))"),
            @Mapping(target = "child", expression = "java(toIdNameDtoList(categoryEntity.getChild()))")
    })
    public abstract CategoryDto.CategoryResponseDto toResponseDto(CategoryEntity categoryEntity);

    abstract CategoryDto.CategoryIdNameDto toIdNameDto(CategoryEntity categoryEntity);
    abstract List<CategoryDto.CategoryIdNameDto> toIdNameDtoList(List<CategoryEntity> categoryEntityList);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "parentCategoryName",target = "parent", qualifiedByName = "categoryNameToCategoryEntity"),
            @Mapping(source = "childCategoryNameList",target = "child", qualifiedByName = "categoryNameListToCategoryEntityList")
    })
    public abstract void updateFromPatchDto(CategoryDto.CategoryPatchDto CategoryPatchDto, @MappingTarget CategoryEntity categoryEntity);

}