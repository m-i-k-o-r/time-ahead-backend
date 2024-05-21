package com.tp.timeAhead.repos;

import com.tp.timeAhead.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("SELECT c FROM Category c WHERE c.user.id = :userId OR c.user IS NULL ORDER BY c.isOverall DESC")
    List<Category> findAll(@Param("userId") UUID userId);

    @Query("SELECT c FROM Category c WHERE c.isOverall = true ORDER BY c.isOverall DESC")
    List<Category> findAllByAdmin();
}