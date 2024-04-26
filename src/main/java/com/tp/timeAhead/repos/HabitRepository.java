package com.tp.timeAhead.repos;

import com.tp.timeAhead.models.Activity;
import com.tp.timeAhead.models.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface HabitRepository extends JpaRepository<Habit, UUID> {
    @Query("SELECT h FROM Habit h WHERE h.user.id = :userId AND LOWER(h.repeatReminder) LIKE CONCAT('%', LOWER(:day), '%')")
    List<Habit> findAllByDay(@Param("userId") UUID userId, @Param("day") String day);

    @Query("SELECT h FROM Habit h WHERE h.user.id = :userId")
    List<Habit> findAll(@Param("userId") UUID userId);
}