package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.dto.CategoryDto;
import com.tp.timeAhead.data.forms.category.CategoryCreateForm;
import com.tp.timeAhead.data.forms.category.CategoryForm;
import com.tp.timeAhead.service.CategoryService;
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
    public CategoryDto createCategory(@RequestBody CategoryCreateForm form) {
        return categoryService.createCategory(form);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable UUID id, @RequestBody CategoryForm form) {
        return categoryService.updateCategory(id, form);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable UUID id) {
        return categoryService.getCategory(id);
    }

    @GetMapping()
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
    }
}
