package com.example.basic_crud_10.controller;

import com.example.basic_crud_10.dto.CourseListResponseDto;
import com.example.basic_crud_10.dto.CourseRequestDto;
import com.example.basic_crud_10.dto.CourseResponseDto;
import com.example.basic_crud_10.dto.ResponseDto;
import com.example.basic_crud_10.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController                      // json 타입으로 받음, 한번에 받을수 있음
@RequestMapping("/api")             // 공통적인 부분 맵핑
@RequiredArgsConstructor                // 서비스 연결
public class CourseController {

    private final CourseService courseService; // 연결준비

    @PostMapping("/save/board")        // 강의 추가, 포스트맵핑, 경로지정
    public ResponseDto saveCourse(@RequestBody CourseRequestDto requestDto) { //객체로 받아올수있는 기능. 바디 안에서 가져와야 함. 받아줄수 있는 객체를 만듬,
        return courseService.saveCourse(requestDto);
    }

    @GetMapping("/get/courses")
    public CourseListResponseDto getCourses() {
        return courseService.getCourses();
    }

    @GetMapping("/get/course")
    public CourseResponseDto getCourse(@RequestParam Long id) {
        return courseService.getCourse(id);
    }

    @PutMapping("/update/course/{id}")
    public CourseResponseDto updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto requestDto) {
        return courseService.updateCourse(id, requestDto);
    }

    @DeleteMapping("/delete/course/{id}")
    public ResponseDto deleteCourse(@PathVariable Long id){
        return courseService.deleteCourse(id);
    }
}
