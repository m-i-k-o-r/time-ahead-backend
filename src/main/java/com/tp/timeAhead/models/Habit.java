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
@Table(name = "habit")
public class Habit {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private String repeatReminder;
    private int numReminder;
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
