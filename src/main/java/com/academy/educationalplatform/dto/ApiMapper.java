package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.*;
import com.academy.educationalplatform.entity.Module;

public final class ApiMapper {

    private ApiMapper() {
    }
//
    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPhone()
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

    public static ModuleResponse toModuleResponse(Module module) {
        return new ModuleResponse(
                module.getId(),
                module.getName(),
                module.getDescription(),
                module.getLessonNumber(),
                module.getCourseId()
        );
    }

    public static LessonResponse toLessonResponse(Lesson lesson) {
        return new LessonResponse(
                lesson.getId(),
                lesson.getName(),
                lesson.getDescription(),
                lesson.getCourseId(),
                lesson.getModuleId()
        );
    }

    public static LikeResponse toLikeResponse(Like like) {
        return new LikeResponse(
                like.getId(),
                like.getUserId(),
                like.getLessonId(),
                like.getCreatedAt()
        );
    }

    public static UserRoleResponse toUserRoleResponse(UserRole userRole) {
        return new UserRoleResponse(
                userRole.getId(),
                userRole.getUserId(),
                userRole.getRole()
        );
    }


    public static EnglishTestResponse toTestResponse(EnglishTest englishTest) {
        return new EnglishTestResponse(
                englishTest.getId(),
                englishTest.getName(),
                englishTest.getDescription()
        );
    }

    public static UserCourseResponse toUserCourseResponse(UserCourse userCourse) {
        return new UserCourseResponse(
                userCourse.getId(),
                userCourse.getUserId(),
                userCourse.getCourseId()
        );
    }
}
