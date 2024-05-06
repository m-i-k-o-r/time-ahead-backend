package com.tp.timeAhead.services;

import com.tp.timeAhead.data.mappers.CategoryMapper;
import com.tp.timeAhead.data.requests.category.CategoryCreateRequest;
import com.tp.timeAhead.data.requests.category.CategoryUpdateRequest;
import com.tp.timeAhead.data.responses.CategoryDto;
import com.tp.timeAhead.exceptions.NotFoundException;
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

    public CategoryDto createCategory(CategoryCreateRequest form) {
        return CategoryMapper.INSTANCE.toDto(categoryRepository.save(Category.builder()
                .name(form.name())
                .user(userRepository.findById(form.userId()).orElseThrow(() -> new NotFoundException("Пользователь с этим id не найден")))
                .build()));
    }

    public CategoryDto updateCategory(UUID id, CategoryUpdateRequest form) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Категория с этим id не найдена"));
        category.setName(form.name());
        return CategoryMapper.INSTANCE.toDto(categoryRepository.save(category));
    }

    public CategoryDto getCategory(UUID id) {
        return CategoryMapper.INSTANCE.toDto(categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Категория с этим id не найдена")));
    }

    public List<CategoryDto> getAllCategory(UUID userId) {
        return CategoryMapper.INSTANCE.toDto(categoryRepository.findAll(userId));
    }

    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
