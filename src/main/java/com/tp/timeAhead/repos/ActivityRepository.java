package com.tp.timeAhead.repos;

import com.tp.timeAhead.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, UUID> {

    @Query("SELECT a FROM Activity a WHERE DATE(a.startTime) <= :data AND (a.endTime IS NULL OR DATE(a.endTime) >= :data) ORDER BY a.endTime ASC NULLS FIRST, a.startTime ASC")
    List<Activity> findAllByTime(@Param("data") LocalDate data);

    @Query("SELECT a FROM Activity a WHERE a.category.id = :categoryId AND (DATE(a.startTime) <= :data AND (a.endTime IS NULL OR DATE(a.endTime) >= :data)) ORDER BY a.endTime ASC NULLS FIRST, a.startTime ASC")
    List<Activity> findAllByTimeAndCategoryId(@Param("data") LocalDate data, @Param("categoryId") UUID categoryId);
}