package com.tp.timeAhead.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime reminder;
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
