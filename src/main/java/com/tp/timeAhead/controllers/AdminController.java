package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.requests.admin.AdminRequest;
import com.tp.timeAhead.data.responses.AdminDto;
import com.tp.timeAhead.data.responses.AuthenticationDto;
import com.tp.timeAhead.services.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Operation(
            summary = "Аутентифицировать администратора",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Администратор аутентифицирован")
            })
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirements
    @PostMapping("/authenticate")
    public AuthenticationDto authenticate(@RequestBody AdminRequest form) {
        return adminService.authenticate(form);
    }

    @Operation(
            summary = "Изменить администратора",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Администратор изменен")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public AdminDto update(@PathVariable UUID id, @RequestBody AdminRequest form) {
        return adminService.updateAdmin(id, form);
    }
}
