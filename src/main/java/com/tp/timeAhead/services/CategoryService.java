package com.tp.timeAhead.services;

import com.tp.timeAhead.data.mappers.CategoryMapper;
import com.tp.timeAhead.data.requests.category.CategoryRequest;
import com.tp.timeAhead.data.responses.CategoryDto;
import com.tp.timeAhead.exceptions.ForbiddenException;
import com.tp.timeAhead.exceptions.NotFoundException;
import com.tp.timeAhead.models.Category;
import com.tp.timeAhead.models.User;
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

    private final JwtService jwtService;

    public CategoryDto createCategory(CategoryRequest form) {
        Category category;
        String role = jwtService.extractAuthorities();
        if (role.contains("ADMIN")) {
            category = Category.builder()
                    .name(form.name())
                    .isOverall(true)
                    .build();
        } else {
            category = Category.builder()
                    .name(form.name())
                    .user(userRepository.findById(((User) jwtService.extractUserDetails()).getId())
                            .orElseThrow(() -> new NotFoundException("Пользователь с этим id не найден")))
                    .isOverall(false)
                    .build();
        }
        return CategoryMapper.INSTANCE.toDto(categoryRepository.save(category));
    }

    public CategoryDto updateCategory(UUID id, CategoryRequest form) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Категория с этим id не найдена"));

        String role = jwtService.extractAuthorities();
        if (category.isOverall()) {
            if (role.contains("ADMIN")) {
                category.setName(form.name());
            } else {
                throw new ForbiddenException("У вас не та роль, чтобы изменять эту категорию");
            }
        } else {
            if (role.contains("USER")) {
                category.setName(form.name());
            } else {
                throw new ForbiddenException("У вас не та роль, чтобы изменять эту категорию");
            }
        }

        return CategoryMapper.INSTANCE.toDto(categoryRepository.save(category));
    }

    public CategoryDto getCategory(UUID id) {
        return CategoryMapper.INSTANCE.toDto(categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Категория с этим id не найдена")));
    }

    public List<CategoryDto> getAllCategory() {
        String role = jwtService.extractAuthorities();
        if (role.contains("ADMIN")) {
            return CategoryMapper.INSTANCE.toDto(categoryRepository.findAllByAdmin());
        } else {
            return CategoryMapper.INSTANCE.toDto(
                    categoryRepository.findAll(
                            ((User) jwtService.extractUserDetails()).getId()
                    ));
        }
    }

    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Категория с этим id не найдена"));

        String role = jwtService.extractAuthorities();
        if (category.isOverall()) {
            if (role.contains("ADMIN")) {
                categoryRepository.deleteById(id);
            } else {
                throw new ForbiddenException("У вас не та роль, чтобы удалять эту категорию");
            }
        } else {
            if (role.contains("USER")) {
                categoryRepository.deleteById(id);
            } else {
                throw new ForbiddenException("У вас не та роль, чтобы удалять эту категорию");
            }
        }
        categoryRepository.deleteById(id);
    }
}
