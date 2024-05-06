package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.responses.UserDto;
import com.tp.timeAhead.data.requests.user.UserRequest;
import com.tp.timeAhead.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public UserDto createUser(@RequestBody UserRequest form) {
        return userService.createUser(form);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable UUID id, @RequestBody UserRequest form) {
        return userService.updateUser(id, form);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.getAllUser();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
