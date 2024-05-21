package com.tp.timeAhead.services;

import com.tp.timeAhead.data.mappers.AdminMapper;
import com.tp.timeAhead.data.requests.AdminRequest;
import com.tp.timeAhead.data.responses.AdminDto;
import com.tp.timeAhead.data.responses.AuthenticationDto;
import com.tp.timeAhead.exceptions.NotFoundException;
import com.tp.timeAhead.models.Admin;
import com.tp.timeAhead.repos.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationDto authenticate(AdminRequest form) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        form.login(),
                        form.password()
                )
        );
        Admin admin = adminRepository.findByLogin(form.login())
                .orElseThrow(() -> new NotFoundException("Администратор с этим логином не найден"));
        var jwtToken = jwtService.generateToken(admin);
        return AuthenticationDto.builder()
                .id(admin.getId())
                .accessToken(jwtToken)
                .build();
    }

    public AdminDto updateAdmin(UUID id, AdminRequest form) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Администратор с этим логином не найден"));

        admin.setLogin(form.login());
        admin.setPassword(passwordEncoder.encode(form.password()));

        return AdminMapper.INSTANCE.toDto(adminRepository.save(admin));
    }
}
