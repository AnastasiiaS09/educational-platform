package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

public interface LikeRepository extends JpaRepository<Like, Long> {
    public Like saveLike(Like like);

    public boolean existsById(Long likeId);

    public Like findLikeById(Long likeId);

    public Like findLikeByTimestamp(Instant createdAt);

    public void deleteLikeById(Long likeId);
}
