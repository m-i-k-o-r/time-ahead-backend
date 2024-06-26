package com.tp.timeAhead.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                // Своего рода отключение CORS (разрешение запросов со всех доменов)
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOriginPatterns(List.of("*"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
                // Настройка доступа к конечным точкам
                // Можно указать конкретный путь, * - 1 уровень вложенности, ** - любое количество уровней вложенности
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/user/register", "/user/authenticate").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user/{id}").hasAuthority( "USER")
                        .requestMatchers(HttpMethod.DELETE, "/user/{id}").hasAuthority("ADMIN")
                        .requestMatchers("/user/{id}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("/user").hasAuthority("ADMIN")

                        .requestMatchers("/admin/authenticate").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")

                        .requestMatchers("/category/**").hasAnyAuthority("ADMIN", "USER")

                        .requestMatchers("/activity/**", "/habit/**", "/task/**").hasAuthority("USER")

                        .requestMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
