package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Like;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Like addLike(Long userId, Long lessonId) {
        try {
            Like like = new Like();
            like.setUserId(userId);
            like.setLessonId(lessonId);
            like.setCreatedAt(Instant.now());

            return likeRepository.save(like);
        } catch (RuntimeException e) {
            throw e;
        }
    }


    public Like findById(Long id) {
        return likeRepository.findById(id).orElseThrow(() -> {
            throw PlatformException.of(PlatformErrorCode.LIKE_NOT_FOUND);
        });
    }


    public void deleteLike(Long id) {
        try {
            if (!likeRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.LIKE_NOT_FOUND);
            }

            likeRepository.deleteById(id);
            System.out.println("Like was deleted successfully");
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
