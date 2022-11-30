package com.example.basic_crud_10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequestDto {
    private String title;
    private String instructor;
    private Double cost;
}
