package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.Like;
import com.academy.educationalplatform.service.LikeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponse create(@Valid @RequestBody LikeRequest request) {
        var like = likeService.addLike(
                request.getUserId(),
                request.getLessonId()
        );
        return toResponse(like);
    }


    @GetMapping("/{id}")
    public LikeResponse getById(@PathVariable Long id) {
        return toResponse(likeService.findById(id));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        likeService.deleteLike(id);
    }

    private LikeResponse toResponse(Like like) {
        return ApiMapper.toLikeResponse(
                like
        );
    }
}
