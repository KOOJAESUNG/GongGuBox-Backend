package com.gonggubox.dto.item;

import com.gonggubox.domain.item.CategoryEntity;
import lombok.*;

import java.util.List;

public class CategoryDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoryPostDto {

        private String name;

        private CategoryEntity parent;

        private List<CategoryEntity> child;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoryResponseDto {
        private Long id;

        private String name;

        private CategoryEntity parent;

        private List<CategoryEntity> child;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoryPatchDto {
        private Long id;

        private String name;

        private CategoryEntity parent;

        private List<CategoryEntity> child;

    }
}
