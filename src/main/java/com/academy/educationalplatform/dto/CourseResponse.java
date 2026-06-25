package com.academy.educationalplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private Long id;

    private String courseName;

    private String description;

    private int lectureNumber;

}
