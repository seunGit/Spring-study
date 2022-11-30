package com.example.basic_crud_10.service;

import com.example.basic_crud_10.dto.CourseListResponseDto;
import com.example.basic_crud_10.dto.CourseRequestDto;
import com.example.basic_crud_10.dto.CourseResponseDto;
import com.example.basic_crud_10.dto.ResponseDto;
import com.example.basic_crud_10.entity.Course;
import com.example.basic_crud_10.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional
    public ResponseDto saveCourse(CourseRequestDto requestDto) {
        courseRepository.save(new Course(requestDto));
        return new ResponseDto("강의 등록 성공", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public CourseListResponseDto getCourses() {
        CourseListResponseDto courseListResponseDto = new CourseListResponseDto();

        List<Course> courses =  courseRepository.findAll();
        for (Course course : courses) {
            courseListResponseDto.addCourse(new CourseResponseDto(course));
        }
        return courseListResponseDto;
    }

    @Transactional(readOnly = true)
    public CourseResponseDto getCourse(Long id) {
        Course course = checkCourse(courseRepository, id);
        return new CourseResponseDto(course);
    }


    @Transactional
    public CourseResponseDto updateCourse(Long id, CourseRequestDto responseDto) {
        Course course = checkCourse(courseRepository, id);
        course.update(responseDto);
        return new CourseResponseDto(course);
    }

    @Transactional
    public ResponseDto deleteCourse(Long id) {
        Course course = checkCourse(courseRepository, id);
        courseRepository.delete(course);
        return new ResponseDto("강의 삭제 성공", HttpStatus.OK.value());
    }

    private Course checkCourse(CourseRepository courseRepository, Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("강의를 찾을 수 없다")
        );
    }
}
