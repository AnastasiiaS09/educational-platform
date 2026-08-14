package com.academy.educationalplatform.dto;

import com.academy.educationalplatform.entity.*;
import com.academy.educationalplatform.entity.Module;
import com.academy.educationalplatform.entity.Course;

public final class ApiMapper {

    private ApiMapper() {
    }

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getUserName(),
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
                course.getCourseName(),
                course.getDescription(),
                course.getModuleQuantity()
        );
    }

    public static ModuleResponse toModuleResponse(Module module) {
        return new ModuleResponse(
                module.getId(),
                module.getModuleName(),
                module.getDescription(),
                module.getLessonQuantity(),
                module.getCourseId(),
                module.getModuleNumber()
        );
    }

    public static LessonResponse toLessonResponse(Lesson lesson) {
        return new LessonResponse(
                lesson.getId(),
                lesson.getLessonName(),
                lesson.getDescription(),
                lesson.getModuleId(),
                lesson.getLessonNumber(),
                lesson.getText(),
                lesson.getType()
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


    public static EnglishTestResponse toEnglishTestResponse(EnglishTest englishTest) {
        return new EnglishTestResponse(
                englishTest.getId(),
                englishTest.getTestName(),
                englishTest.getDescription(),
                englishTest.getMaxScore(),
                englishTest.getQuestionQuantity()
        );
    }
    public static UserCourseResponse toUserCourseResponse(UserCourse userCourse) {
        return new UserCourseResponse(
                userCourse.getId(),
                userCourse.getUserId(),
                userCourse.getCourseId()
        );
    }

    public static EnglishTestQuestionResponse toEnglishTestQuestionResponse(EnglishTestQuestion englishTestQuestion) {
        return new EnglishTestQuestionResponse(
                englishTestQuestion.getId(),
                englishTestQuestion.getTestId(),
                englishTestQuestion.getQuestionText(),
                englishTestQuestion.getQuestionNumber(),
                englishTestQuestion.getScore()
        );
    }

    public static AnswerOptionResponse toAnswerOptionResponse(AnswerOption answerOption) {
        return new AnswerOptionResponse(
                answerOption.getId(),
               answerOption.getQuestionId(),
                answerOption.getOptionText(),
                answerOption.isCorrect()
        );
    }

    public static UserLessonResponse toUserLessonResponse(UserLesson userLesson) {
        return new UserLessonResponse(
                userLesson.getId(),
                userLesson.getUserId(),
                userLesson.getLessonId(),
                userLesson.getStatus()
        );
    }

    public static UserModuleResponse toUserModuleResponse(UserModule userModule) {
        return new UserModuleResponse(
                userModule.getId(),
                userModule.getUserId(),
                userModule.getModuleId(),
                userModule.getStatus()
        );
    }

}
