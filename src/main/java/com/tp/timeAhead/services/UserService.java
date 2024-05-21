package com.tp.timeAhead.services;

import com.tp.timeAhead.data.mappers.UserMapper;
import com.tp.timeAhead.data.requests.UserRequest;
import com.tp.timeAhead.data.responses.AuthenticationDto;
import com.tp.timeAhead.data.responses.UserDto;
import com.tp.timeAhead.exceptions.NotFoundException;
import com.tp.timeAhead.models.User;
import com.tp.timeAhead.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationDto registration(UserRequest form) {
        User user = userRepository.save(User.builder()
                .email(form.email())
                .password(passwordEncoder.encode(form.password()))
                .dateRegistration(LocalDateTime.now())
                .build());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationDto.builder()
                .id(user.getId())
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationDto authenticate(UserRequest form) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        form.email(),
                        form.password()
                )
        );
        User user = userRepository.findByEmail(form.email())
                .orElseThrow(() -> new NotFoundException("Пользователь с этой почтой не найден"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationDto.builder()
                .id(user.getId())
                .accessToken(jwtToken)
                .build();
    }

    public UserDto updateUser(UUID id, UserRequest form) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с этим id не найден"));

        user.setEmail(form.email());
        user.setPassword(passwordEncoder.encode(form.password()));

        return UserMapper.INSTANCE.toDto(userRepository.save(user));
    }

    public UserDto getUser(UUID id) {
        return UserMapper.INSTANCE.toDto(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с этим id не найден")));
    }

    public List<UserDto> getAllUser() {
        return UserMapper.INSTANCE.toDto(userRepository.findAll());
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
