package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.responses.CategoryDto;
import com.tp.timeAhead.data.requests.category.CategoryCreateRequest;
import com.tp.timeAhead.data.requests.category.CategoryUpdateRequest;
import com.tp.timeAhead.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping()
    public CategoryDto createCategory(@RequestBody CategoryCreateRequest form) {
        return categoryService.createCategory(form);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable UUID id, @RequestBody CategoryUpdateRequest form) {
        return categoryService.updateCategory(id, form);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable UUID id) {
        return categoryService.getCategory(id);
    }

    @GetMapping()
    public List<CategoryDto> getAllCategories(@RequestParam UUID userId) {
        return categoryService.getAllCategory(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
    }
}
