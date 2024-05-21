package com.tp.timeAhead.data.mappers;

import com.tp.timeAhead.data.responses.AdminDto;
import com.tp.timeAhead.models.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    AdminDto toDto(Admin item);

    List<AdminDto> toDto(List<Admin> items);
}

