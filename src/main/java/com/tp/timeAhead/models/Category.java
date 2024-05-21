package com.tp.timeAhead.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private boolean isOverall;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
