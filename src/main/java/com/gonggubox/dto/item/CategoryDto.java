package com.gonggubox.dto.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

public class CategoryDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoryPostDto {

        @NotBlank
        private String categoryName;

        private String parentCategoryName; //null 이 아니라면 해당하는 부모 카테고리 아래에 카테고리를 생성

//        private List<CategoryEntity> childCategoryName; //상위 카테고리만 알면 될듯??

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoryResponseDto {
        private Long categoryId;

        private String categoryName;

        private CategoryIdNameDto parentCategoryInfo;

        private List<CategoryIdNameDto> childCategoryInfoList;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoryIdNameDto {
        private Long categoryId;

        private String categoryName;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoryPatchDto {

        @NotNull
        @Min(1)
        private Long categoryId;

        private String categoryName;

        private String parentCategoryName;

        private List<String> childCategoryNameList;

    }
}
