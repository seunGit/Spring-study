package com.example.basic_crud_10.dto;

import com.example.basic_crud_10.entity.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseResponseDto {
    private String title;
    private String instructor;
    private Double cost;

    public CourseResponseDto(Course course) {
        this.title = course.getTitle();
        this.instructor = course.getInstructor();
        this.cost = course.getCost();
    }
}
