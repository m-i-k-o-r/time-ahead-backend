package com.tp.timeAhead.services;

import com.tp.timeAhead.data.responses.UserDto;
import com.tp.timeAhead.data.requests.user.UserRequest;
import com.tp.timeAhead.data.mappers.UserMapper;
import com.tp.timeAhead.exceptions.NotFoundException;
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

    public UserDto createUser(UserRequest form) {
        return UserMapper.INSTANCE.toDto(userRepository.save(User.builder()
                .email(form.email())
                .password(form.password())
                .build()));
    }

    public UserDto updateUser(UUID id, UserRequest form) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with this id not found"));
        user.setEmail(form.email());
        user.setPassword(form.password());
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
