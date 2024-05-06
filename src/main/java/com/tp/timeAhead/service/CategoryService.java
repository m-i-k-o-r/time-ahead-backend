package com.tp.timeAhead.service;

import com.tp.timeAhead.data.dto.CategoryDto;
import com.tp.timeAhead.data.forms.category.CategoryCreateForm;
import com.tp.timeAhead.data.forms.category.CategoryForm;
import com.tp.timeAhead.data.mappers.CategoryMapper;
import com.tp.timeAhead.exception.NotFoundException;
import com.tp.timeAhead.models.Category;
import com.tp.timeAhead.repos.CategoryRepository;
import com.tp.timeAhead.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryDto createCategory(CategoryCreateForm form) {
        return CategoryMapper.INSTANCE.toDto(categoryRepository.save(Category.builder()
                .name(form.name())
                .user(userRepository.findById(form.userId()).orElseThrow(() -> new NotFoundException("User with this id not found")))
                .build()));
    }

    public CategoryDto updateCategory(UUID id, CategoryForm form) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with this id not found"));
        category.setName(form.name());
        return CategoryMapper.INSTANCE.toDto(categoryRepository.save(category));
    }

    public CategoryDto getCategory(UUID id) {
        return CategoryMapper.INSTANCE.toDto(categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with this id not found")));
    }

    public List<CategoryDto> getAllCategory(UUID userId) {
        return CategoryMapper.INSTANCE.toDto(categoryRepository.findAll(userId));
    }

    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
