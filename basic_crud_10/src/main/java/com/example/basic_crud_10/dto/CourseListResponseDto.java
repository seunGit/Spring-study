package com.example.basic_crud_10.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CourseListResponseDto extends ResponseDto{

    List<CourseResponseDto> courseList = new ArrayList<>();

    public CourseListResponseDto(){
        super("강의 목록 조회 성공", HttpStatus.OK.value());
    }

    public void addCourse(CourseResponseDto responseDto) {
        courseList.add(responseDto);
    }
}
