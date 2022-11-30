package com.example.basic_crud_10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequestDto {
    private String title;       // 받아주는 부분 설정
    private String instructor;
    private Double cost;
}
