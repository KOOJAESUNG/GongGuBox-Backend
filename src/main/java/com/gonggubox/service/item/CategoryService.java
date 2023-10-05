package com.gonggubox.service.item;

import com.gonggubox.domain.item.CategoryEntity;
import com.gonggubox.dto.item.CategoryDto;
import com.gonggubox.mapper.item.CategoryMapper;
import com.gonggubox.repository.item.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Transactional
    public CategoryDto.CategoryResponseDto addCategory(CategoryDto.CategoryPostDto categoryPostDto) {
        if (categoryPostDto.getParentCategoryName() != null) {
            CategoryEntity parentCategory = categoryRepository.findByName(categoryPostDto.getParentCategoryName()).orElseThrow(EntityNotFoundException::new);
            parentCategory.getChildList().add(categoryMapper.toEntity(categoryPostDto));
        } else {
            categoryRepository.save(categoryMapper.toEntity(categoryPostDto));
        }
        return categoryMapper.toResponseDto(categoryRepository.findByName(categoryPostDto.getCategoryName()).orElseThrow(EntityNotFoundException::new));
    }

    public CategoryDto.CategoryResponseDto getCategoryById(Long categoryId) {
        return categoryMapper.toResponseDto(categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new));
    }

    public CategoryDto.CategoryResponseDto getCategoryByName(String categoryName) {
        return categoryMapper.toResponseDto(categoryRepository.findByName(categoryName).orElseThrow(EntityNotFoundException::new));
    }

    public void getCategoryTree() {

    }

    @Transactional
    public CategoryDto.CategoryResponseDto updateCategory(CategoryDto.CategoryPatchDto categoryPatchDto) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryPatchDto.getCategoryId()).orElseThrow(EntityNotFoundException::new);
        categoryMapper.updateFromPatchDto(categoryPatchDto,categoryEntity);
        return categoryMapper.toResponseDto(categoryRepository.findById(categoryPatchDto.getCategoryId()).orElseThrow(EntityNotFoundException::new));
    }

//    @Transactional
//    public CategoryDto.CategoryResponseDto changeParentCategory() {
//
//    }
//
//    @Transactional
//    public CategoryDto.CategoryResponseDto changeChildCategory() {
//
//    }

    @Transactional
    public String deleteCategory(Long categoryId) {
        String name = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new).getName();
        categoryRepository.deleteById(categoryId);
        return "삭제한 Category의 name : "+name;
    }
}
