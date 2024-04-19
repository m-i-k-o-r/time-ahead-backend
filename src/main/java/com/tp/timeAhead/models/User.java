package com.tp.timeAhead.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String email;
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Category> categories;
}
