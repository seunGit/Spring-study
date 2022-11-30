package com.example.basic_crud_10.controller;

import com.example.basic_crud_10.dto.CourseListResponseDto;
import com.example.basic_crud_10.dto.CourseRequestDto;
import com.example.basic_crud_10.dto.CourseResponseDto;
import com.example.basic_crud_10.dto.ResponseDto;
import com.example.basic_crud_10.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/save/course")
    public ResponseDto saveCourse(@RequestBody CourseRequestDto requestDto) {
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
