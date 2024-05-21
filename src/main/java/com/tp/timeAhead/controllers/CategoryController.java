package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.requests.CategoryRequest;
import com.tp.timeAhead.data.responses.CategoryDto;
import com.tp.timeAhead.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(
            summary = "Создать категорию",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Категория создана")
            })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public CategoryDto createCategory(@RequestBody CategoryRequest form) {
        return categoryService.createCategory(form);
    }

    @Operation(
            summary = "Изменить категорию",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Категория изменена")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable UUID id, @RequestBody CategoryRequest form) {
        return categoryService.updateCategory(id, form);
    }

    @Operation(
            summary = "Получить категорию",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Категория получена")
            })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable UUID id) {
        return categoryService.getCategory(id);
    }

    @Operation(
            summary = "Получить все категории",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все категории получены")
            })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @Operation(
            summary = "Удалить категорию",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Категория удалена")
            })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
    }
}
