package com.tp.timeAhead.data.mappers;

import com.tp.timeAhead.data.responses.CategoryDto;
import com.tp.timeAhead.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toDto(Category item);

    List<CategoryDto> toDto(List<Category> items);
}
