package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.Comment;
import com.academy.educationalplatform.entity.Course;
import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.User;

public final class ApiMapper {

    private ApiMapper() {
    }

    public static UserResponse toUserResponse(User user) {
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

    public static CourseResponse toCourseResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getLectureNumber()
        );
    }
}
