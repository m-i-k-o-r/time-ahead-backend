package com.tp.timeAhead.service;

import com.tp.timeAhead.data.dto.UserDto;
import com.tp.timeAhead.data.forms.user.UserForm;
import com.tp.timeAhead.data.mappers.UserMapper;
import com.tp.timeAhead.exception.NotFoundException;
import com.tp.timeAhead.models.User;
import com.tp.timeAhead.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto createUser(UserForm form) {
        return UserMapper.INSTANCE.toDto(userRepository.save(User.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .build()));
    }

    public UserDto updateUser(UUID id, UserForm form) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with this id not found"));
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        return UserMapper.INSTANCE.toDto(userRepository.save(user));
    }

    public UserDto getUser(UUID id) {
        return UserMapper.INSTANCE.toDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with this id not found")));
    }

    public List<UserDto> getAllUser() {
        return UserMapper.INSTANCE.toDto(userRepository.findAll());
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
