package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Comment;
import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.User;

import java.util.List;

public final class ApiMapper {

    private ApiMapper() {
    }

    public static UserResponse toUserResponse(User user, List<Role> roles) {
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPhone(),
            roles.stream().map(Role::name).toList()
        );
    }



    public static CommentResponse toCommentResponse(Comment c) {
        return new CommentResponse(
            c.getId(),
            c.getUserId(),
            c.getText(),
            c.getCreatedAt(),
            c.getLikeCount()
        );
    }
}
