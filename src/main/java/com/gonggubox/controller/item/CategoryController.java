package com.gonggubox.controller.item;

import com.gonggubox.dto.item.CategoryDto;
import com.gonggubox.service.item.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody @Valid CategoryDto.CategoryPostDto categoryPostDto) {
        return ResponseEntity.ok().body(categoryService.addCategory(categoryPostDto));
    }

    @GetMapping("/getCategoryById")
    public ResponseEntity<?> getCategoryById(@RequestParam @NotNull @Min(1) Long categoryId) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(categoryId));
    }

    @GetMapping("/getCategoryByName")
    public ResponseEntity<?> getCategoryByName(@RequestParam @NotBlank String categoryName) {
        return ResponseEntity.ok().body(categoryService.getCategoryByName(categoryName));
    }

//    @GetMapping("/getCategoryTree")
//    public ResponseEntity<?> getCategoryTree() {
//        return ResponseEntity.ok().body(categoryService.getCategoryTree());
//    }

    @PatchMapping("/updateCategory")
    public ResponseEntity<?> updateCategory(@RequestBody @Valid CategoryDto.CategoryPatchDto categoryPatchDto) {
        return ResponseEntity.ok().body(categoryService.updateCategory(categoryPatchDto));
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<?> deleteCategory(@RequestParam @NotNull @Min(1) Long categoryId) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(categoryId));
    }
}
