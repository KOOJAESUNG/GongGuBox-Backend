package com.gonggubox.service.item;

import com.gonggubox.dto.item.CategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CategoryService {
    @Transactional
    public void createCategory(CategoryDto.CategoryPostDto CategoryPostDto) {

    }
    public void getCategory(Long CategoryId) {

    }
    @Transactional
    public void updateCategory(CategoryDto.CategoryPatchDto CategoryPatchDto) {

    }
    @Transactional
    public void deleteCategory(Long CategoryId) {

    }
}
