package com.tp.timeAhead.repos;

import com.tp.timeAhead.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId AND t.isDone = :isDone ORDER BY t.reminder ASC")
    List<Task> findAllByOrderByReminderAsc(@Param("userId") UUID userId, @Param("isDone") boolean isDone);

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId AND t.isDone = :isDone ORDER BY t.reminder DESC")
    List<Task> findAllByOrderByReminderDesc(@Param("userId") UUID userId, @Param("isDone") boolean isDone);
}